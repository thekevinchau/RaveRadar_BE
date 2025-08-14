package com.project.RaveRadar.controllers;

import com.project.RaveRadar.DTO.UserProfileDTO;
import com.project.RaveRadar.exceptions.ForbiddenException;
import com.project.RaveRadar.models.User;
import com.project.RaveRadar.payloads.UserRegPayload;
import com.project.RaveRadar.security.JwtUtil;
import com.project.RaveRadar.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager manager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user, HttpServletResponse response) {
        return userService.cookieLogin(user, response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response){
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // Deletes cookie
        response.addCookie(cookie);

        return ResponseEntity.ok("Logged out");
    }

    @PostMapping("/register")
    public ResponseEntity<UserProfileDTO> register(@RequestBody @Valid UserRegPayload payload){
        return userService.register(payload);
    }
}
