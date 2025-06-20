package com.project.RaveRadar.DTO;

import com.project.RaveRadar.models.Artist;
import com.project.RaveRadar.models.Event;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistDTO {

    private UUID id;

    private String stageName;

    private String realName;

    private String bio;

    private String avatarUrl;

    private String country;

    private String hometown;

    private boolean isVerified;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public ArtistDTO(Artist artist) {
        this.id = artist.getId();
        this.stageName = artist.getStageName();
        this.realName = artist.getRealName();
        this.bio = artist.getBio();
        this.avatarUrl = artist.getAvatarUrl();
        this.country = artist.getCountry();
        this.hometown = artist.getHometown();
        this.isVerified = artist.isVerified();
        this.createdAt = artist.getCreatedAt();
        this.updatedAt = artist.getUpdatedAt();
    }

}
