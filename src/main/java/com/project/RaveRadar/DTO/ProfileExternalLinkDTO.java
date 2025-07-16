package com.project.RaveRadar.DTO;

import com.project.RaveRadar.models.UserProfileLink;
import lombok.Data;

@Data
public class ProfileExternalLinkDTO {
    private String platform;
    private String link;

    public ProfileExternalLinkDTO(UserProfileLink externalLink){
        this.platform = String.valueOf(externalLink.getPlatform());
        this.link = externalLink.getExternalLink();
    }
}
