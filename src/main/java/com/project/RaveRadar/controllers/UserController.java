package com.project.RaveRadar.controllers;

import com.project.RaveRadar.DTO.EventDTO;
import com.project.RaveRadar.DTO.UserProfileDTO;
import com.project.RaveRadar.payloads.UserProfileEdit;
import com.project.RaveRadar.services.S3Service;
import com.project.RaveRadar.services.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/profiles")
@AllArgsConstructor
public class UserController {
    private final UserProfileService profileService;
    private final S3Service s3Service;

    @PostMapping("/links/{id}")
    public ResponseEntity<UserProfileDTO> addExternalLinks(@PathVariable UUID id, @RequestBody UserProfileEdit edits){
        return profileService.addProfileExternalLink(id, edits.getExternalLink());
    }
    @GetMapping("/me")
    public ResponseEntity<UserProfileDTO> getMyProfile(){
        return profileService.getMyProfile();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable UUID id){
        return profileService.getUserProfile(id);
    }
    @GetMapping("/favorite-events/{id}")
    public ResponseEntity<Set<EventDTO>> getFavoriteEvents(@PathVariable UUID id){
        return profileService.getFavoriteEvents(id);
    }

    @GetMapping("/upload-image-url")
    public ResponseEntity<URL> generateAvatarUploadUrl(@RequestParam String contentType){
        return ResponseEntity.ok(s3Service.generateProfilePictureUploadUrl(contentType));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserProfileDTO> editUserProfile(@PathVariable UUID id, @RequestBody UserProfileEdit edits){
        return profileService.editUserProfile(id, edits);
    }
    @PatchMapping("/links/{userId}")
    public ResponseEntity<UserProfileDTO> editExternalLinks(@PathVariable UUID userId, @RequestBody UserProfileEdit edits){
        System.out.println(userId);
        return profileService.editProfileExternalLinks(userId, edits.getExternalLink());
    }
    @PostMapping("/favorite-events/{id}")
    public ResponseEntity<?> favoriteEvent(@PathVariable UUID id){
        return profileService.favoriteEvent(id);
    }

    @DeleteMapping("/favorite-events/{eventId}")
    public ResponseEntity<?> unfavoriteEvent(@PathVariable UUID eventId){
        return profileService.unfavoriteEvent(eventId);
    }

    @DeleteMapping("/links/{profileId}/{linkId}")
    public ResponseEntity<String> deleteExternalLink(@PathVariable UUID profileId, @PathVariable UUID linkId){
        return profileService.deleteProfileExternalLink(profileId, linkId);
    }

}
