package com.project.RaveRadar.services;

import com.project.RaveRadar.DTO.EventRatingDTO;
import com.project.RaveRadar.exceptions.ForbiddenException;
import com.project.RaveRadar.exceptions.NotFoundException;
import com.project.RaveRadar.models.Event;
import com.project.RaveRadar.models.EventRating;
import com.project.RaveRadar.models.User;
import com.project.RaveRadar.models.UserProfile;
import com.project.RaveRadar.payloads.RatingPayload;
import com.project.RaveRadar.repositories.EventRatingRepository;
import com.project.RaveRadar.repositories.EventRepository;
import com.project.RaveRadar.repositories.UserProfileRepository;
import com.project.RaveRadar.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EventRatingService {
    private final EventRatingRepository ratingRepository;
    private final EventRepository eventRepository;
    private final UserProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ResponseEntity<EventRatingDTO> getRating(UUID id){
        Optional<EventRating> ratingOptional = ratingRepository.findById(id);
        if (ratingOptional.isEmpty()){
            throw new NotFoundException("Event review was not found.");
        }
        return ResponseEntity.ok(new EventRatingDTO(ratingOptional.get()));
    }
    public ResponseEntity<EventRatingDTO> createRating(String userEmail, RatingPayload payload){
        if (userRepository.findByEmail(userEmail).isEmpty()){
            throw new NotFoundException("Reviewer does not exist!!!!!!!!!!!!!!");
        }
        User user = userRepository.findByEmail(userEmail).get();
        if (profileRepository.findByUserId(user.getId()).isEmpty()){
            throw new NotFoundException("Reviewer's profile does not exist!");
        }

        if (eventRepository.findById(payload.getEventId()).isEmpty()){
            throw new NotFoundException("Event does not exist!");
        }
        EventRating newRating = new EventRating();
        newRating.setUserProfile(profileRepository.findByUserId(user.getId()).get());
        newRating.setEvent(eventRepository.findById(payload.getEventId()).get());
        newRating.setRatingSoundQuality(payload.getSoundQualityRating());
        newRating.setRatingCrowdVibes(payload.getCrowdRating());
        newRating.setRatingBathrooms(payload.getBathroomRating());
        newRating.setRatingOverall(payload.getOverallRating());
        newRating.setRatingVenue(payload.getVenueRating());
        newRating.setCreatedAt(LocalDateTime.now());
        newRating.setComment(payload.getComment());
        EventRating savedRating = ratingRepository.save(newRating);
        return ResponseEntity.ok(new EventRatingDTO(savedRating));
    }

    public ResponseEntity<EventRatingDTO> editRating(UUID ratingId, String userEmail, RatingPayload payload){
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("Original user account does not exist"));
        UserProfile profile = profileRepository.findByUserId(user.getId())
                .orElseThrow(() -> new NotFoundException("Reviewer profile does not exist"));
        EventRating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new NotFoundException("This review does not exist"));
        Event event = eventRepository.findById(payload.getEventId())
                .orElseThrow(() -> new NotFoundException("Event does not exist"));

        //Check if the user profile of the rating is equal to the profile retrieved from the @AuthenticationPrincipal passed in as the userEmail to this method.
        if (!rating.getUserProfile().getId().equals(profile.getId())){
            throw new ForbiddenException("You cannot edit someone else's review!");
        }

        rating.setUserProfile(profile);
        rating.setEvent(event);
        rating.setRatingSoundQuality(payload.getSoundQualityRating());
        rating.setRatingCrowdVibes(payload.getCrowdRating());
        rating.setRatingBathrooms(payload.getBathroomRating());
        rating.setRatingOverall(payload.getOverallRating());
        rating.setRatingVenue(payload.getVenueRating());
        rating.setUpdatedAt(LocalDateTime.now());
        rating.setComment(payload.getComment());
        rating.setEdited(true);
        return ResponseEntity.ok(new EventRatingDTO(ratingRepository.save(rating)));

    }
}
