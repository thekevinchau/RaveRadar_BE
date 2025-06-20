package com.project.RaveRadar.DTO;

import com.project.RaveRadar.models.Artist;
import lombok.Data;

import java.util.UUID;

@Data
public class FavoriteArtistDTO {
    private UUID id;
    private String stageName;

    public FavoriteArtistDTO(Artist artist){
        this.id = artist.getId();
        this.stageName = artist.getStageName();
    }
}
