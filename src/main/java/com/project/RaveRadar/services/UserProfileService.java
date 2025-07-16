package com.project.RaveRadar.services;


import com.project.RaveRadar.models.ProfilePersonalDetails;
import com.project.RaveRadar.models.User;
import com.project.RaveRadar.models.UserProfile;
import com.project.RaveRadar.repositories.ProfilePersonalDetailsRepository;
import com.project.RaveRadar.repositories.UserProfileRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Service
@AllArgsConstructor
public class UserProfileService {
    private final UserProfileRepository profileRepository;
    private final ProfilePersonalDetailsRepository personalDetailsRepository;

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

}
