package com.project.RaveRadar.payloads;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class RatingPayload {


    @NotNull(message = "eventId is required")
    private UUID eventId;

    @Min(value = 1)
    @Max(value = 5)
    private Integer soundQualityRating;

    @Min(value = 1)
    @Max(value = 5)
    private Integer bathroomRating;

    @Min(value = 1)
    @Max(value = 5)
    private Integer crowdRating;

    @Min(value = 1)
    @Max(value = 5)
    private Integer venueRating;

    @Min(value = 1)
    @Max(value = 5)
    private Integer overallRating;

    private String comment;
}