package com.project.RaveRadar.controllers;

import com.project.RaveRadar.DTO.UserProfileDTO;
import com.project.RaveRadar.payloads.UserProfileEdit;
import com.project.RaveRadar.services.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-profile")
@AllArgsConstructor
public class UserProfileController {
    private final UserProfileService profileService;
    @PatchMapping()
    public ResponseEntity<UserProfileDTO> editProfile (@RequestBody UserProfileEdit edits){
        return profileService.editUserProfile(edits);
    }
}
