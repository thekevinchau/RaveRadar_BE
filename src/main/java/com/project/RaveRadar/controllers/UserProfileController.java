package com.project.RaveRadar.controllers;

import com.project.RaveRadar.DTO.UserProfileDTO;
import com.project.RaveRadar.payloads.UserProfileEdit;
import com.project.RaveRadar.services.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user-profile")
@AllArgsConstructor
public class UserProfileController {
    private final UserProfileService profileService;

    @GetMapping("")
    public ResponseEntity<UserProfileDTO> getProfileByUsername(@RequestParam String username){
        return profileService.getProfileByUsername(username);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getProfileById(@PathVariable UUID id){
        return profileService.getProfileById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<UserProfileDTO> getPrincipalProfile(){
        return profileService.getPrincipalProfile();
    }

    @PatchMapping()
    public ResponseEntity<UserProfileDTO> editProfile (@RequestBody UserProfileEdit edits){
        return profileService.editUserProfile(edits);
    }
}
