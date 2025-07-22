package com.project.RaveRadar.controllers;

import com.project.RaveRadar.models.Announcement;
import com.project.RaveRadar.services.AnnouncementService;
import lombok.AllArgsConstructor;
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

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    public ResponseEntity<?> deleteAnnouncement(@PathVariable UUID id){
        return announcementService.deleteAnnouncement(id);
    }
}
