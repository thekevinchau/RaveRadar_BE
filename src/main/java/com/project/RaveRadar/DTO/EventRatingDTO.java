package com.project.RaveRadar.DTO;
import com.project.RaveRadar.models.EventRating;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Getter
public class EventRatingDTO {
    private UUID id;
    private SimpleUserProfileDTO reviewer;
    private SimpleEventDTO event;
    private Integer soundQualityRating;
    private Integer bathroomRating;
    private Integer crowdRating;
    private Integer venueRating;
    private Integer overallRating;
    private boolean isEdited;
    private String comment;
    private LocalDate createdTime;

    public EventRatingDTO(EventRating rating) {
        this.id = rating.getId();

        // Assuming you have a constructor or method to convert UserProfile to UserProfileDTO
        this.reviewer = new SimpleUserProfileDTO(rating.getUserProfile());

        // Assuming you have a constructor or method to convert Event to SimpleEventDTO
        this.event = new SimpleEventDTO(rating.getEvent());

        this.soundQualityRating = rating.getRatingSoundQuality();
        this.bathroomRating = rating.getRatingBathrooms();
        this.crowdRating = rating.getRatingCrowdVibes();
        this.venueRating = rating.getRatingVenue();
        this.overallRating = rating.getRatingOverall();
        this.comment = rating.getComment();
        this.isEdited = rating.isEdited();
        if (rating.getCreatedAt() != null) {
            this.createdTime = rating.getCreatedAt().toLocalDate();
        }
    }

}
