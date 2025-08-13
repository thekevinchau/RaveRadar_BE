package com.project.RaveRadar.repositories;

import com.project.RaveRadar.DTO.CommentDTO;
import com.project.RaveRadar.models.AnnouncementComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnnouncementCommentRepo extends JpaRepository<AnnouncementComment, UUID> {
    List<AnnouncementComment> findAllByAnnouncementId(UUID announcementId);
    @Query("SELECT c FROM AnnouncementComment c WHERE c.id =:id  ORDER BY c.createdAt DESC LIMIT 5")
    List<AnnouncementComment> findFiveCommentsMostRecent(@Param("id") UUID id);
    long countByAnnouncementId(UUID announcementId);
}
