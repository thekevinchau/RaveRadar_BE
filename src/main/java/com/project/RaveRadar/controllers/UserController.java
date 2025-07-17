package com.project.RaveRadar.controllers;

import com.project.RaveRadar.DTO.UserProfileDTO;
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

import java.util.UUID;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserProfileService profileService;

    @PostMapping("/profiles/links/{id}")
    public ResponseEntity<UserProfileDTO> addExternalLinks(@PathVariable UUID id, @RequestBody UserProfileEdit edits){
        return profileService.addProfileExternalLink(id, edits.getExternalLinks());
    }

    @GetMapping("/profiles/{id}")
    public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable UUID id){
        return profileService.getUserProfile(id);
    }
    @PatchMapping("/profiles/{id}")
    public ResponseEntity<UserProfileDTO> editUserProfile(@PathVariable UUID id, @RequestBody UserProfileEdit edits){
        return profileService.editUserProfile(id, edits);
    }
    @PatchMapping("/profiles/links/{id}")
    public ResponseEntity<UserProfileDTO> editExternalLinks(@PathVariable UUID id, @RequestBody UserProfileEdit edits){
        return profileService.editProfileExternalLinks(id, edits.getExternalLinks());
    }
    @DeleteMapping("/profiles/links/{profileId}/{linkId}")
    public ResponseEntity<String> deleteExternalLink(@PathVariable UUID profileId, @PathVariable UUID linkId){
        return profileService.deleteProfileExternalLink(profileId, linkId);
    }

}
