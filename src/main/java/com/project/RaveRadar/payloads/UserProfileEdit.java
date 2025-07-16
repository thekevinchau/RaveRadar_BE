package com.project.RaveRadar.payloads;

import com.project.RaveRadar.DTO.PersonalDetailsDTO;
import com.project.RaveRadar.enums.Gender;
import lombok.Data;

@Data
public class UserProfileEdit {

    private String displayName;
    private Gender gender;
    private String bio;
    private String avatarUrl;
    PersonalDetailsDTO personalDetails;
}
