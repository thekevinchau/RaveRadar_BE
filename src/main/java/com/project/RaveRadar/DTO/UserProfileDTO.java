package com.project.RaveRadar.DTO;

import com.project.RaveRadar.models.UserProfile;
import lombok.Data;

@Data
public class UserProfileDTO {
    private String name;
    private String username;
    private String bio;
    private String avatarPath;
    private String pronouns;

    public UserProfileDTO (UserProfile profile){
        this.name = profile.getName();
        this.username = profile.getUsername();
        this.bio = profile.getBio();
        this.avatarPath = profile.getAvatarPath();
        this.pronouns = profile.getPronouns();
    }
}
