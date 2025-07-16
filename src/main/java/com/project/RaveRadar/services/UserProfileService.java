package com.project.RaveRadar.services;


import com.project.RaveRadar.DTO.ProfileExternalLinkDTO;
import com.project.RaveRadar.DTO.UserProfileDTO;
import com.project.RaveRadar.enums.Gender;
import com.project.RaveRadar.exceptions.NotFoundException;
import com.project.RaveRadar.models.ProfilePersonalDetails;
import com.project.RaveRadar.models.User;
import com.project.RaveRadar.models.UserProfile;
import com.project.RaveRadar.models.UserProfileLink;
import com.project.RaveRadar.payloads.UserProfileEdit;
import com.project.RaveRadar.repositories.ProfilePersonalDetailsRepository;
import com.project.RaveRadar.repositories.UserProfileLinkRepository;
import com.project.RaveRadar.repositories.UserProfileRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
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

    @Transactional
    private ProfilePersonalDetails createProfilePersonalDetails(LocalDate birthday, String phoneNumber){
        ProfilePersonalDetails details = new ProfilePersonalDetails();
        details.setBirthday(birthday);
        if (phoneNumber != null){
            details.setPhoneNumber(phoneNumber);
        }
        return personalDetailsRepository.save(details);
    }

    @Transactional
    public UserProfile createUserProfile (User user, String displayName, LocalDate birthday, String phoneNumber){
        UserProfile newProfile = new UserProfile();
        newProfile.setUser(user);
        newProfile.setDisplayName(displayName);
        ProfilePersonalDetails userPersonalDetails = createProfilePersonalDetails(birthday, phoneNumber);
        newProfile.setPersonalDetails(userPersonalDetails);
        return profileRepository.save(newProfile);
    }

    private UserProfile handleProfileEdits(UserProfile profile, String displayName, Gender gender, String bio, String avatarUrl){
        if (displayName != null){
            profile.setDisplayName(displayName);
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
        details.setBirthday(LocalDate.parse(birthday));
        details.setPhoneNumber(phoneNumber);
        return personalDetailsRepository.save(details);
    }

    @Transactional
    public ResponseEntity<UserProfileDTO> editUserProfile (UUID userId, UserProfileEdit edits){
        Optional<UserProfile> profile = profileRepository.findById(userId);
        if (profile.isEmpty()){
            throw new NotFoundException("User not found.");
        }
        UserProfile queriedProfile = profile.get();
        UserProfile updatedProfile = handleProfileEdits(queriedProfile, edits.getDisplayName(), edits.getGender(), edits.getBio(), edits.getAvatarUrl());
        if (edits.getPersonalDetails() != null){
            String birthday = String.valueOf(edits.getPersonalDetails().getBirthday());
            String phoneNumber = edits.getPersonalDetails().getPhoneNumber();
            //this gets saved
            ProfilePersonalDetails detailsEdits = handlePersonalDetailsEdits(queriedProfile.getPersonalDetails(), birthday, phoneNumber);
            updatedProfile.setPersonalDetails(detailsEdits);
        }
        return ResponseEntity.ok(new UserProfileDTO(profileRepository.save(updatedProfile)));
    }

    @Transactional
    public ResponseEntity<UserProfileDTO> addProfileExternalLink (UUID profileId, Set<UserProfileLink> links){
        Optional<UserProfile> profile = profileRepository.findById(profileId);
        if (profile.isEmpty()){
            throw new NotFoundException("User was not found!");
        }
        links.forEach(link -> {
            link.setUserProfile(profile.get());
        });
        UserProfileDTO profileDTO = new UserProfileDTO(profile.get());
        List<UserProfileLink> savedLinks = profileLinkRepository.saveAll(links);
        Set<ProfileExternalLinkDTO> savedDTOs = savedLinks.stream().map(ProfileExternalLinkDTO::new).collect(Collectors.toSet());
        profileDTO.setExternalLinkDTOs(savedDTOs);
        return ResponseEntity.ok(profileDTO);
    }

}
