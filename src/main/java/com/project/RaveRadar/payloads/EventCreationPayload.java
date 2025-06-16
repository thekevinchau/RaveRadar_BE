package com.project.RaveRadar.payloads;

import com.project.RaveRadar.enums.AgeRestriction;
import com.project.RaveRadar.enums.EdmGenre;
import com.project.RaveRadar.enums.EventType;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;


@Data
@NoArgsConstructor
public class EventCreationPayload {

    @NotBlank(message = "Event name is required")
    private String eventName;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Event type is required")
    private EventType eventType;

    @NotNull(message = "Genre is required")
    private EdmGenre genre;

    @NotBlank(message = "External link is required")
    @Pattern(
            regexp = "^(https?://)?[\\w.-]+(?:\\.[\\w\\.-]+)+[/#?]?.*$",
            message = "Invalid URL format"
    )
    private String externalLink;

    @NotNull(message = "Age restriction is required")
    private AgeRestriction ageRestriction;

    @NotNull(message = "Start date is required")
    @Future(message = "Start date must be in the future")
    private ZonedDateTime startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private ZonedDateTime endDate;
}
