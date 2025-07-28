package com.project.RaveRadar.services;

import com.project.RaveRadar.DTO.AnnouncementDTO;
import com.project.RaveRadar.DTO.CommentDTO;
import com.project.RaveRadar.exceptions.ForbiddenException;
import com.project.RaveRadar.exceptions.NotFoundException;
import com.project.RaveRadar.models.Announcement;
import com.project.RaveRadar.models.AnnouncementComment;
import com.project.RaveRadar.payloads.AnnouncementEdit;
import com.project.RaveRadar.repositories.AnnouncementCommentRepo;
import com.project.RaveRadar.repositories.AnnouncementRepo;
import com.project.RaveRadar.utils.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepo announcementRepo;
    private final AnnouncementCommentRepo commentRepo;
    private final UserProfileService profileService;
    private final AuthUtil authUtil;

    private Announcement getAnnouncementObj(UUID id){
        return announcementRepo.findById(id).orElseThrow(() -> new NotFoundException("Announcement was not found."));
    }

    public ResponseEntity<AnnouncementDTO> getAnnouncement(UUID id){
        Announcement announcement = announcementRepo.findById(id).orElseThrow(() -> new NotFoundException("Announcement was not found."));
        return ResponseEntity.ok(new AnnouncementDTO(announcement));
    }

    public ResponseEntity<Page<AnnouncementDTO>> getAllAnnouncements(Pageable pageable){
        Page<Announcement> announcementPage = announcementRepo.findAll(pageable);
        Page<AnnouncementDTO> dtoPage = announcementPage.map(AnnouncementDTO::new);

        return ResponseEntity.ok(dtoPage);
    }

    @Transactional
    public ResponseEntity<AnnouncementDTO> createAnnouncement (Announcement announcement) {
        authUtil.isUserAdmin();
        Announcement newAnnouncement = new Announcement();
        newAnnouncement.setAnnouncer(profileService.getPrincipalProfile());
        newAnnouncement.setHeader(announcement.getHeader());
        newAnnouncement.setContent(announcement.getContent());
        newAnnouncement.setCreatedAt(Instant.now());
        return ResponseEntity.ok(new AnnouncementDTO(announcementRepo.save(newAnnouncement)));
    }

    @Transactional
    public ResponseEntity<String> deleteAnnouncement(UUID announcementId){
        authUtil.isUserAdmin();
        Announcement queriedAnnouncement = announcementRepo.findById(announcementId).orElseThrow(
                () -> new NotFoundException("Announcement to be deleted was not found.")
        );
        System.out.println(queriedAnnouncement);
        announcementRepo.delete(queriedAnnouncement);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Transactional
    public ResponseEntity<AnnouncementDTO> editAnnouncement(UUID id, AnnouncementEdit edits){
        authUtil.isUserAdmin();
        Announcement queriedAnnouncement = announcementRepo.findById(id).orElseThrow(() -> new NotFoundException("Announcement not found."));
        if (queriedAnnouncement.getAnnouncer().getUser() != authUtil.getCurrentUser()){
            throw new ForbiddenException("This is not your announcement to edit");
        }
        if (edits.getHeader() != null){
            queriedAnnouncement.setHeader(edits.getHeader());
        }
        if (edits.getContent() != null){
            queriedAnnouncement.setContent(edits.getContent());
        }
        queriedAnnouncement.setUpdatedAt(Instant.now());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new AnnouncementDTO(announcementRepo.save(queriedAnnouncement)));
    }

    @AllArgsConstructor
    @Data
    public static class CommentPayload{
        private String content;
    }

    @Transactional
    public ResponseEntity<CommentDTO> createComment(UUID announcementId, CommentPayload payload){
        AnnouncementComment comment = new AnnouncementComment();
        comment.setAnnouncement(getAnnouncementObj(announcementId));
        comment.setCommenter(profileService.getPrincipalProfile());
        comment.setContent(payload.getContent());
        comment.setCreatedAt(Instant.now());
        return ResponseEntity.ok(new CommentDTO(commentRepo.save(comment)));
    }

    public ResponseEntity<List<CommentDTO>> getAllCommentsByAnnouncement(UUID announcementId){
        return ResponseEntity.ok(commentRepo.findAllByAnnouncementId(announcementId).stream().map(CommentDTO::new).toList());
    }
}
