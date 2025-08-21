package com.project.RaveRadar.DTO;

import com.project.RaveRadar.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EventDetails {
    private String eventName;
    private String description;
    private EventType eventType;
    private Instant startDate;
    private Instant endDate;
}
