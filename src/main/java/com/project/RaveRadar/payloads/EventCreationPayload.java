package com.project.RaveRadar.payloads;


import jakarta.validation.constraints.NotNull;
import lombok.*;


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

