package com.project.RaveRadar.DTO;

import com.project.RaveRadar.models.Event;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class SimpleEventDTO {
    private UUID eventId;
    private String eventName;
    private String eventImagePath;
    private ZonedDateTime eventDate;

    public SimpleEventDTO(Event event){
        this.eventId = event.getEventId();
        this.eventName = event.getName();
        this.eventImagePath = event.getImagePath();
        this.eventDate = event.getStartDate();
    }
}
