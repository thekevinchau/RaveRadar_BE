package com.project.RaveRadar.payloads;

import com.project.RaveRadar.DTO.PersonalDetailsDTO;
import com.project.RaveRadar.enums.Gender;
import com.project.RaveRadar.models.UserProfileLink;
import lombok.Data;

import java.util.Set;

@Data
public class UserProfileEdit {

    private String displayName;
    private Gender gender;
    private String bio;
    private String avatarUrl;
    PersonalDetailsDTO personalDetails;
    Set<UserProfileLink> externalLinks;
}
