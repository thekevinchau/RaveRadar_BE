package com.project.RaveRadar.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class EventListPayload {
    @NotNull
    @NotEmpty
    List<EventCreationPayload> eventPayload;
}
