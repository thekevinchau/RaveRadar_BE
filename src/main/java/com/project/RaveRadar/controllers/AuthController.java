package com.project.RaveRadar.controllers;

import com.project.RaveRadar.models.User;
import com.project.RaveRadar.payloads.UserRegPayload;
import com.project.RaveRadar.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody User user){
        return userService.login(user);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserRegPayload payload){
        return userService.register(payload);
    }
}
