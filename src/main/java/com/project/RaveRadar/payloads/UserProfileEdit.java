package com.project.RaveRadar.payloads;

import com.project.RaveRadar.DTO.PersonalDetailsDTO;
import com.project.RaveRadar.enums.Gender;
import com.project.RaveRadar.models.UserProfileLink;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

import java.util.Set;

@Getter
public class UserProfileEdit {

    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @Pattern(
            regexp = "^[a-zA-Z0-9._-]{3,20}$",
            message = "Username can only contain letters, numbers, underscores, hyphens, and periods"
    )
    private String displayName;

    private Gender gender;

    @Size(max = 1000, message = "Bio cannot exceed 1000 characters.")
    private String bio;

    private String avatarUrl;

    PersonalDetailsDTO personalDetails;
    Set<UserProfileLink> externalLinks;
}
