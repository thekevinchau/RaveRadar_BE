package com.project.RaveRadar.DTO;

import com.project.RaveRadar.models.UserProfileLink;
import lombok.Data;

import java.util.UUID;

@Data
public class ProfileExternalLinkDTO {
    private UUID id;
    private String platform;
    private String link;

    public ProfileExternalLinkDTO(UserProfileLink externalLink){
        this.id = externalLink.getId();
        this.platform = String.valueOf(externalLink.getPlatform());
        this.link = externalLink.getExternalLink();
    }
}
