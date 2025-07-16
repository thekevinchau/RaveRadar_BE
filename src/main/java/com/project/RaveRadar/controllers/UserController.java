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
    private final AuthUtil authUtil;


    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody User user){
        return userService.login(user);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserRegPayload payload){
        return userService.register(payload);
    }
    @PatchMapping("/profiles/{id}")
    public ResponseEntity<UserProfileDTO> editUserProfile(@PathVariable UUID id, @RequestBody UserProfileEdit edits){
        return profileService.editUserProfile(id, edits);
    }
    @PostMapping("/profiles/external-links/{id}")
    public ResponseEntity<UserProfileDTO> addProfileExternalLinks(@PathVariable UUID id, @RequestBody UserProfileEdit edits){
        return profileService.addProfileExternalLink(id, edits.getExternalLinks());
    }
}
