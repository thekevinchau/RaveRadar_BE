package com.project.RaveRadar.DTO;

import com.project.RaveRadar.models.AnnouncementComment;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class CommentDTO {
    private UUID id;
    private UUID announcementId;
    private SimpleUserProfileDTO commenter;
    private String content;
    private Instant createdAt;

    public CommentDTO(AnnouncementComment comment){
        this.id = comment.getId();
        this.announcementId = comment.getAnnouncement().getId();
        this.commenter = new SimpleUserProfileDTO(
                comment.getCommenter().getId(),
                comment.getCommenter().getDisplayName(),
                comment.getCommenter().getAvatarUrl());
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }
}
