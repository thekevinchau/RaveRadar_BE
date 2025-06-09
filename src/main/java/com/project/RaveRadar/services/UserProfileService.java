package com.project.RaveRadar.services;

import com.project.RaveRadar.DTO.UserProfileDTO;
import com.project.RaveRadar.models.User;
import com.project.RaveRadar.models.UserProfile;
import com.project.RaveRadar.repositories.UserProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserProfileService {
    private final UserProfileRepository profileRepository;

    //only need two fields when first creating the user profile
    public UserProfileDTO createUserProfile(User user, String username){
        UserProfile profile = new UserProfile();
        profile.setUser(user);
        profile.setUsername(username);
        return new UserProfileDTO(profileRepository.save(profile));
    }
}
