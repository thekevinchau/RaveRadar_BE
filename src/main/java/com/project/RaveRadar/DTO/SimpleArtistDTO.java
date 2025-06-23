package com.project.RaveRadar.DTO;

import com.project.RaveRadar.models.Artist;
import lombok.Data;

import java.util.UUID;

@Data
public class SimpleArtistDTO {
    private UUID id;
    private String avatarUrl;
    private String stageName;

    public SimpleArtistDTO(Artist artist){
        this.id = artist.getId();
        this.avatarUrl = artist.getAvatarUrl();
        this.stageName = artist.getStageName();
    }
}
