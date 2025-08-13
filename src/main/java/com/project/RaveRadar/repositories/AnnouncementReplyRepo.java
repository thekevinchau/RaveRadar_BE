package com.project.RaveRadar.repositories;

import com.project.RaveRadar.models.AnnouncementCommentReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnnouncementReplyRepo extends JpaRepository<AnnouncementCommentReply, UUID> {
}
