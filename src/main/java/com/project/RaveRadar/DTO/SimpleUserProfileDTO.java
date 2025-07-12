package com.project.RaveRadar.DTO;

import com.project.RaveRadar.models.UserProfile;
import lombok.Data;

import java.util.UUID;

@Data
public class SimpleUserProfileDTO {
    private UUID id;
    private String username;

    public SimpleUserProfileDTO(UserProfile profile){
        this.id = profile.getId();
        this.username = profile.getUsername();
    }
}
