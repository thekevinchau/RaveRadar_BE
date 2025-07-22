package com.project.RaveRadar.DTO;

import com.project.RaveRadar.models.Announcement;
import com.project.RaveRadar.models.User;
import com.project.RaveRadar.models.UserProfile;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class AnnouncementDTO {
    private UUID id;
    private SimpleUserProfileDTO announcer;
    private String header;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;

    public AnnouncementDTO(Announcement announcement){
        UserProfile announcer = announcement.getAnnouncer();
        this.id = announcement.getId();
        this.announcer = new SimpleUserProfileDTO(announcer.getId(), announcer.getDisplayName(), announcer.getAvatarUrl());
        this.header = announcement.getHeader();
        this.content = announcement.getContent();
        this.createdAt = announcement.getCreatedAt();
        this.updatedAt = announcement.getUpdatedAt();
    }

}
