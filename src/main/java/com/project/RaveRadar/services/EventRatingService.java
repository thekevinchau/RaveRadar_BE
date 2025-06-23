package com.project.RaveRadar.services;

import com.project.RaveRadar.DTO.EventRatingDTO;
import com.project.RaveRadar.exceptions.NotFoundException;
import com.project.RaveRadar.models.EventRating;
import com.project.RaveRadar.repositories.EventRatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EventRatingService {
    private final EventRatingRepository ratingRepository;

    public ResponseEntity<EventRatingDTO> getRating(UUID id){
        Optional<EventRating> ratingOptional = ratingRepository.findById(id);
        if (ratingOptional.isEmpty()){
            throw new NotFoundException("Event review was not found.");
        }
        return ResponseEntity.ok(new EventRatingDTO(ratingOptional.get()));
    }
}
