package com.project.RaveRadar.repositories;

import com.project.RaveRadar.models.AnnouncementCommentReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CommentReplyRepo extends JpaRepository<AnnouncementCommentReply, UUID> {
    @Query("SELECT r FROM AnnouncementCommentReply r WHERE r.comment.id =:commentId")
    List<AnnouncementCommentReply> getAllRepliesByCommentId(@Param("commentId") UUID commentId);
}
