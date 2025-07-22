package com.project.RaveRadar.services;

import com.project.RaveRadar.DTO.AnnouncementDTO;
import com.project.RaveRadar.exceptions.ForbiddenException;
import com.project.RaveRadar.exceptions.NotFoundException;
import com.project.RaveRadar.models.Announcement;
import com.project.RaveRadar.models.User;
import com.project.RaveRadar.payloads.AnnouncementEdit;
import com.project.RaveRadar.repositories.AnnouncementRepo;
import com.project.RaveRadar.utils.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepo announcementRepo;
    private final UserProfileService profileService;
    private final AuthUtil authUtil;

    @Transactional
    public ResponseEntity<AnnouncementDTO> createAnnouncement (Announcement announcement) {
        authUtil.isUserAdmin();
        Announcement newAnnouncement = new Announcement();
        newAnnouncement.setAnnouncer(profileService.getMyProfile());
        newAnnouncement.setHeader(announcement.getHeader());
        newAnnouncement.setContent(announcement.getContent());
        newAnnouncement.setCreatedAt(Instant.now());
        return ResponseEntity.ok(new AnnouncementDTO(announcementRepo.save(newAnnouncement)));
    }

    @Transactional
    public ResponseEntity<?> deleteAnnouncement(UUID announcementId){
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
        return ResponseEntity.ok(new AnnouncementDTO(queriedAnnouncement));
    }
}
