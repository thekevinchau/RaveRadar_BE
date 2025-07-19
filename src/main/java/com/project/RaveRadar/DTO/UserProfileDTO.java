package com.project.RaveRadar.DTO;

import com.project.RaveRadar.models.Event;
import com.project.RaveRadar.models.UserProfile;
import com.project.RaveRadar.models.UserProfileLink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class UserProfileDTO {
    private UUID id;
    private String displayName;
    private String gender;
    private String bio;
    private String avatarUrl;
    PersonalDetailsDTO personalDetailsDTO;
    Set<ProfileExternalLinkDTO> externalLinks;
    Set<FavoriteEvent> favoriteEvents;
    private Instant createdAt;
    private Instant updatedAt;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FavoriteEvent {
        private UUID id;
        private String name;

        public FavoriteEvent(Event event) {
            this.id = event.getId();
            this.name = event.getEventName();
        }
    }

    public UserProfileDTO (UserProfile profile){
        this.id = profile.getId();
        this.displayName = profile.getDisplayName();
        this.gender = profile.getGender();
        this.bio = profile.getBio();
        this.avatarUrl = profile.getAvatarUrl();
        this.createdAt = profile.getCreatedAt();
        this.updatedAt = profile.getUpdatedAt();
        this.favoriteEvents = profile.getFavoriteEvents().stream().map(FavoriteEvent::new).collect(Collectors.toSet());
        this.personalDetailsDTO = new PersonalDetailsDTO(profile.getPersonalDetails());
    }
}
