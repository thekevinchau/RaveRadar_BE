package com.project.RaveRadar.controllers;

import com.project.RaveRadar.DTO.EventRatingDTO;
import com.project.RaveRadar.payloads.RatingPayload;
import com.project.RaveRadar.services.EventRatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/ratings")
@AllArgsConstructor
public class RatingController {
    private final EventRatingService ratingService;

    @GetMapping("/review/{id}")
    public ResponseEntity<EventRatingDTO> getRating(@PathVariable UUID id){
        return ratingService.getRating(id);
    }

    @PostMapping("/review")
    public ResponseEntity<?> createUserRating(@AuthenticationPrincipal UserDetails user, @RequestBody RatingPayload payload){
        System.out.println("Current user" + user);
        return ratingService.createRating(user.getUsername(), payload);
    }

}
