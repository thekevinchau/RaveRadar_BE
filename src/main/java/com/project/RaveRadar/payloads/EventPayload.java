package com.project.RaveRadar.payloads;

import com.project.RaveRadar.enums.EventType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.Instant;

@Getter
public class EventPayload {
    @NotBlank(message = "Event name is required.")
    private String eventName;

    private String description;

    @NotNull(message = "Event type is required.")
    private EventType eventType;

    private String avatarUrl;
    private String bannerUrl;

    @NotBlank(message = "Address is required.")
    private String address;

    @Size(max=255, message = "City name must be less than 255 characters.")
    @NotBlank(message = "City is required.")
    private String city;

    @Size(max = 2,message = "State must be abbreviated to two letters.")
    @NotBlank(message = "State is required.")
    private String state;

    @Size(max = 5)
    @NotBlank(message = "Zipcode is required")
    private String zipCode;

    @Size(max=255, message = "Venue name cannot exceed 255 characters.")
    private String venueName;

    @NotNull(message = "Start date is required.")
    @Future(message = "Start date must be in the future.")
    private Instant startDate;

    @Future(message = "End date must be in the future.")
    private Instant endDate;

}
