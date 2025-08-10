package com.project.RaveRadar.services;


import com.project.RaveRadar.DTO.ProfileExternalLinkDTO;
import com.project.RaveRadar.DTO.UserProfileDTO;
import com.project.RaveRadar.enums.Gender;
import com.project.RaveRadar.exceptions.ForbiddenException;
import com.project.RaveRadar.exceptions.NotFoundException;
import com.project.RaveRadar.exceptions.ResourceAlreadyExistsException;
import com.project.RaveRadar.models.*;
import com.project.RaveRadar.payloads.UserProfileEdit;
import com.project.RaveRadar.repositories.ProfilePersonalDetailsRepository;
import com.project.RaveRadar.repositories.UserProfileLinkRepository;
import com.project.RaveRadar.repositories.UserProfileRepository;
import com.project.RaveRadar.utils.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserProfileService {
    private final UserProfileRepository profileRepository;
    private final ProfilePersonalDetailsRepository personalDetailsRepository;
    private final UserProfileLinkRepository profileLinkRepository;
    private final EventService eventService;
    private final AuthUtil authUtil;

    //Checks if the current user that is logged in matches the same user that the profile is being queried on.
    public boolean isUserProfileOwner(UserProfile profile){
        User user = authUtil.getCurrentUser();
        return user.getId().equals(profile.getUser().getId());
    }

    public UserProfile getPrincipalProfile(){
        return profileRepository.findByUser(authUtil.getCurrentUser()).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Transactional
    private ProfilePersonalDetails createProfilePersonalDetails(LocalDate birthday, String phoneNumber){
        ProfilePersonalDetails details = new ProfilePersonalDetails();
        details.setBirthday(birthday);
        if (phoneNumber != null){
            details.setPhoneNumber(phoneNumber);
        }
        return personalDetailsRepository.save(details);
    }


    private UserProfile handleProfileEdits(UserProfile profile, String displayName, Gender gender, String bio, String avatarUrl){
        if (displayName != null) {
            profileRepository.findByDisplayName(displayName).ifPresent(existingProfile -> {
                if (!existingProfile.getId().equals(profile.getId())) {
                    throw new ResourceAlreadyExistsException("A user with that name already exists!");
                }
            });
            profile.setDisplayName(displayName.toLowerCase());
        }
        if (bio != null){
            profile.setBio(bio);
        }
        if (gender != null){
            profile.setGender(String.valueOf(gender));
        }
        if (avatarUrl != null){
            profile.setAvatarUrl(avatarUrl);
        }
        profile.setUpdatedAt(Instant.now());
        return profile;
    }

    @Transactional
    private ProfilePersonalDetails handlePersonalDetailsEdits (ProfilePersonalDetails details, String birthday, String phoneNumber){
        if (birthday != null && !birthday.isEmpty() && !birthday.equalsIgnoreCase("null")){
            details.setBirthday(LocalDate.parse(birthday));
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()){
                details.setPhoneNumber(phoneNumber);
        }
        return personalDetailsRepository.save(details);
    }


    public ResponseEntity<UserProfileDTO> getUserProfile (UUID id){
        Optional<UserProfile> profile = profileRepository.findById(id);
        if (profile.isEmpty()){
            throw new NotFoundException("Profile not found");
        }
        //this will allow access to the private information
        if (isUserProfileOwner(profile.get())){
            Set<ProfileExternalLinkDTO> externalLinks = profileLinkRepository.findByUserProfile(profile.get()).stream()
                    .map(ProfileExternalLinkDTO::new).collect(Collectors.toSet());
            UserProfileDTO dto = new UserProfileDTO(profile.get());
            dto.setExternalLinks(externalLinks);
            return ResponseEntity.ok(dto);
        }
        //profile for public use
        else{
            UserProfileDTO returnedProfile = new UserProfileDTO(profile.get());
            returnedProfile.setPersonalDetailsDTO(null);
            return ResponseEntity.ok(returnedProfile);
        }
    }

    @Transactional
    public UserProfile createUserProfile (User user, String displayName, LocalDate birthday, String phoneNumber){
        UserProfile newProfile = new UserProfile();
        newProfile.setUser(user);
        newProfile.setDisplayName(displayName.toLowerCase());
        ProfilePersonalDetails userPersonalDetails = createProfilePersonalDetails(birthday, phoneNumber);
        newProfile.setPersonalDetails(userPersonalDetails);
        return profileRepository.save(newProfile);
    }

    @Transactional
    public ResponseEntity<UserProfileDTO> editUserProfile (UUID userId, UserProfileEdit edits){
        Optional<UserProfile> profile = profileRepository.findById(userId);
        if (profile.isEmpty()){
            throw new NotFoundException("User not found.");
        }
        if (!isUserProfileOwner(profile.get())){
            throw new ForbiddenException("You are not allowed to access this resource");
        }
        UserProfile queriedProfile = profile.get();
        UserProfile updatedProfile = handleProfileEdits(queriedProfile, edits.getDisplayName(), edits.getGender(), edits.getBio(), edits.getAvatarUrl());
        updatedProfile.setUpdatedAt(Instant.now());
        if (edits.getPersonalDetails() != null){
            String birthday = String.valueOf(edits.getPersonalDetails().getBirthday());
            String phoneNumber = edits.getPersonalDetails().getPhoneNumber();
            ProfilePersonalDetails detailsEdits = handlePersonalDetailsEdits(queriedProfile.getPersonalDetails(), birthday, phoneNumber);
            updatedProfile.setPersonalDetails(detailsEdits);
        }
        return getUserProfile(userId);
    }

    @Transactional
    public ResponseEntity<UserProfileDTO> addProfileExternalLink (UUID profileId, Set<UserProfileLink> links){
        Optional<UserProfile> profile = profileRepository.findById(profileId);
        if (profile.isEmpty()){
            throw new NotFoundException("User was not found!");
        }
        if (!isUserProfileOwner(profile.get())){
            throw new ForbiddenException("You are not allowed to access this resource");
        }
        if (links == null || links.isEmpty()) {
            throw new IllegalArgumentException("No external links provided.");
        }
        links.forEach(link -> {
            Optional<UserProfileLink> currentLink = profileLinkRepository.findByUserProfileAndPlatform(profile.get(), link.getPlatform());
            if (currentLink.isPresent()){
                throw new ResourceAlreadyExistsException(currentLink.get().getPlatform() + " is already on your profile!");
            }
            else {
                link.setUserProfile(profile.get());
            }
        });
        profileLinkRepository.saveAll(links);
        return getUserProfile(profileId);
    }

    @Transactional
    public ResponseEntity<UserProfileDTO> editProfileExternalLinks (UUID profileId, Set<UserProfileLink> links){
        Optional<UserProfile> profile = profileRepository.findById(profileId);
        if (profile.isEmpty()){
            throw new NotFoundException("User was not found!");
        }
        if (!isUserProfileOwner(profile.get())){
            throw new ForbiddenException("You are not allowed to access this resource");
        }
        if (links == null || links.isEmpty()) {
            throw new IllegalArgumentException("No external links provided.");
        }
        links.forEach(link -> {
            UserProfileLink queriedLink = profileLinkRepository.findById(link.getId()).orElseThrow(() -> new NotFoundException("Not found"));
            if (!queriedLink.getUserProfile().equals(profile.get())){
                throw new ForbiddenException("This link does not belong to you to edit");
            }
            queriedLink.setExternalLink(link.getExternalLink());
            queriedLink.setPlatform(link.getPlatform());
            profileLinkRepository.save(queriedLink);
        });
        return getUserProfile(profileId);
    }

    @Transactional
    public ResponseEntity<String> deleteProfileExternalLink (UUID profileId, UUID linkId){
        Optional<UserProfile> profile = profileRepository.findById(profileId);
        if (profile.isEmpty()){
            throw new NotFoundException("User was not found!");
        }
        if (!isUserProfileOwner(profile.get())){
            throw new ForbiddenException("You are not allowed to access this resource");
        }

        //verify if the profileExternalLink belongs to the current profile
        UserProfileLink link = profileLinkRepository.findById(linkId).orElseThrow(() -> new NotFoundException("External link not found."));

        if (link.getUserProfile() != profile.get()){
            throw new ForbiddenException("This link does not belong to you to edit");
        }
        else{
            profileLinkRepository.deleteById(linkId);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully deleted" + link.getPlatform());
    }

    @Transactional
    public ResponseEntity<UserProfileDTO> favoriteEvent(UUID eventId){
        UserProfile profile = getPrincipalProfile();
        Set<Event> favoriteEvents = profile.getFavoriteEvents();
        favoriteEvents.add(eventService.getEventObj(eventId));
        return ResponseEntity.ok(new UserProfileDTO(profileRepository.save(profile)));
    }

    /*
    @Transactional
    public ResponseEntity<UserProfileDTO> unfavoriteEvent(UUID eventId){
        UserProfile profile = getMyProfile();
        Set<Event>
    }

     */
}
