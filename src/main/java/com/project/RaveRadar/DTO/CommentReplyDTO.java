package com.project.RaveRadar.DTO;

import com.project.RaveRadar.models.AnnouncementCommentReply;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class CommentReplyDTO {
    private UUID id;
    private UUID commentId;
    private SimpleUserProfileDTO commenter;
    private String content;
    private Instant createdAt;

    public CommentReplyDTO(AnnouncementCommentReply reply){
        this.id = reply.getId();
        this.commentId = reply.getComment().getId();
        this.commenter = new SimpleUserProfileDTO(reply.getCommenter().getId(), reply.getCommenter().getDisplayName(), reply.getCommenter().getAvatarUrl());
        this.content = reply.getContent();
        this.createdAt = reply.getCreatedAt();
    }
}
