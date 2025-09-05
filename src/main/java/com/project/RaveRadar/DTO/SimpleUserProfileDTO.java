package com.project.RaveRadar.DTO;

import com.project.RaveRadar.models.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleUserProfileDTO {
    private UUID id;
    private String name;
    private String avatarUrl;

    public SimpleUserProfileDTO(UserProfile userProfile){
        this.id = userProfile.getId();
        this.name = userProfile.getDisplayName();
        this.avatarUrl = userProfile.getAvatarUrl();
    }
}
