package com.project.RaveRadar.DTO;

import com.project.RaveRadar.models.UserProfile;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserProfileDTO {
    private String name;
    private String username;
    private String bio;
    private String avatarPath;
    private String pronouns;
    private Set<SimpleArtistDTO> favoriteArtists;
    private Set<SimpleEventDTO> eventHistory;

    public UserProfileDTO (UserProfile profile){
        this.name = profile.getName();
        this.username = profile.getUsername();
        this.bio = profile.getBio();
        this.avatarPath = profile.getAvatarPath();
        this.pronouns = profile.getPronouns();
        this.favoriteArtists = profile.getFavoriteArtists()
                .stream()
                .map(SimpleArtistDTO::new)
                .collect(Collectors.toSet());
        this.eventHistory = profile.getEventHistory()
                .stream()
                .map(SimpleEventDTO::new)
                .collect(Collectors.toSet());
    }
}
