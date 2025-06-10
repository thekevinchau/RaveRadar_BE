package com.project.RaveRadar.services;

import com.project.RaveRadar.DTO.UserProfileDTO;
import com.project.RaveRadar.exceptions.ForbiddenException;
import com.project.RaveRadar.exceptions.NotFoundException;
import com.project.RaveRadar.models.User;
import com.project.RaveRadar.models.UserProfile;
import com.project.RaveRadar.payloads.UserProfileEdit;
import com.project.RaveRadar.repositories.UserProfileRepository;
import com.project.RaveRadar.repositories.UserRepository;
import com.project.RaveRadar.utils.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserProfileService {
    private final UserProfileRepository profileRepository;
    private final AuthUtil util;
    //only need two fields when first creating the user profile

    @Transactional
    public void createUserProfile(User user, String username){
        UserProfile profile = new UserProfile();
        profile.setUser(user);
        profile.setUsername(username);
        profileRepository.save(profile);
    }

    @Transactional
    public ResponseEntity<UserProfileDTO> editUserProfile(UserProfileEdit edit){
        User currentUser = util.getCurrentUser();
        Optional<UserProfile> profileOptional = profileRepository.findByUser(currentUser);
        if (profileOptional.isEmpty()){
            throw new NotFoundException("User profile was not found");
        }
        UserProfile profile = profileOptional.get();

        if (!profile.getUser().getId().equals(currentUser.getId())){
            throw new ForbiddenException("You do not have access to this resource.");
        }
        profile.setUsername(edit.getUsername());
        profile.setName(edit.getName());
        profile.setBio(edit.getBio());
        profile.setAvatarPath(edit.getAvatarPath());
        profile.setPronouns(edit.getPronouns());
        return ResponseEntity.ok(new UserProfileDTO(profileRepository.save(profile)));
    }
}
