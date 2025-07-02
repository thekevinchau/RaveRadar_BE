package com.project.RaveRadar.payloads;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class EventListPayload {
    @NotNull
    List<EventCreationPayload> eventPayload;
}
