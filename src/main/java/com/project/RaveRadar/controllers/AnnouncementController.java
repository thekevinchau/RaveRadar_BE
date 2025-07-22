package com.project.RaveRadar.controllers;

import com.project.RaveRadar.DTO.AnnouncementDTO;
import com.project.RaveRadar.models.Announcement;
import com.project.RaveRadar.payloads.AnnouncementEdit;
import com.project.RaveRadar.services.AnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/announcements")
@AllArgsConstructor
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @PostMapping("")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<?> createAnnouncement(@RequestBody Announcement announcement){
        return announcementService.createAnnouncement(announcement);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementDTO> getAnnouncementById(@PathVariable UUID id){
        return announcementService.getAnnouncement(id);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<AnnouncementDTO>> getAllAnnouncements(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt"));
        return announcementService.getAllAnnouncements(pageable);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<String> deleteAnnouncement(@PathVariable UUID id){
        return announcementService.deleteAnnouncement(id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<?> editAnnouncement(@PathVariable UUID id, @RequestBody AnnouncementEdit edit){
        return announcementService.editAnnouncement(id, edit);
    }
}
