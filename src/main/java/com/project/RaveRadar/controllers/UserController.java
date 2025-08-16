package com.project.RaveRadar.controllers;

import com.project.RaveRadar.DTO.EventDTO;
import com.project.RaveRadar.DTO.UserProfileDTO;
import com.project.RaveRadar.models.Event;
import com.project.RaveRadar.models.User;
import com.project.RaveRadar.models.UserProfile;
import com.project.RaveRadar.payloads.UserProfileEdit;
import com.project.RaveRadar.payloads.UserRegPayload;
import com.project.RaveRadar.services.UserProfileService;
import com.project.RaveRadar.services.UserService;
import com.project.RaveRadar.utils.AuthUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/profiles")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserProfileService profileService;

    @PostMapping("/links/{id}")
    public ResponseEntity<UserProfileDTO> addExternalLinks(@PathVariable UUID id, @RequestBody UserProfileEdit edits){
        return profileService.addProfileExternalLink(id, edits.getExternalLinks());
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

    @PatchMapping("/{id}")
    public ResponseEntity<UserProfileDTO> editUserProfile(@PathVariable UUID id, @RequestBody UserProfileEdit edits){
        return profileService.editUserProfile(id, edits);
    }
    @PatchMapping("/links/{id}")
    public ResponseEntity<UserProfileDTO> editExternalLinks(@PathVariable UUID id, @RequestBody UserProfileEdit edits){
        return profileService.editProfileExternalLinks(id, edits.getExternalLinks());
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
