package com.project.RaveRadar.payloads;


import com.project.RaveRadar.DTO.EventDetails;
import com.project.RaveRadar.DTO.ImageURLs;
import com.project.RaveRadar.DTO.Location;
import com.project.RaveRadar.enums.EventType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.Instant;
import java.util.UUID;


@Getter
public class EventCreationPayload {

    private UUID id;

    @NotNull
    private EventDetails eventDetails;

    @NotNull
    private Location location;
    private ImageURLs imageURLs;
}

