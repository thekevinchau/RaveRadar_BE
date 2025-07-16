package com.project.RaveRadar.DTO;

import com.project.RaveRadar.models.UserProfile;
import com.project.RaveRadar.models.UserProfileLink;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class UserProfileDTO {
    private UUID id;
    private String displayName;
    private String gender;
    private String bio;
    private String avatarUrl;
    PersonalDetailsDTO personalDetailsDTO;
    Set<ProfileExternalLinkDTO> externalLinkDTOs;
    private Instant createdAt;
    private Instant updatedAt;

    public UserProfileDTO (UserProfile profile){
        this.id = profile.getId();
        this.displayName = profile.getDisplayName();
        this.gender = profile.getGender();
        this.bio = profile.getBio();
        this.avatarUrl = profile.getAvatarUrl();
        this.createdAt = profile.getCreatedAt();
        this.updatedAt = profile.getUpdatedAt();
        this.personalDetailsDTO = new PersonalDetailsDTO(profile.getPersonalDetails());
    }
}
