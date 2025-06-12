package com.project.RaveRadar.DTO;
import com.project.RaveRadar.models.Event;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class EventDTO {

    private UUID eventId;
    private String name;
    private String description;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private AddressDTO address;
    private String eventType;
    private String genre;
    private String externalLink;
    private String imagePath;
    private String supportEmail;
    private String ageRestriction;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public EventDTO(Event event) {
        this.eventId = event.getEventId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        if (event.getAddress() != null) {
            this.address = new AddressDTO(event.getAddress());
        }
        this.eventType = event.getEventType();
        this.genre = event.getGenre();
        this.externalLink = event.getExternalLink();
        this.imagePath = event.getImagePath();
        this.supportEmail = event.getSupportEmail();
        this.ageRestriction = event.getAgeRestriction();
        this.createdAt = event.getCreatedAt();
        this.updatedAt = event.getUpdatedAt();
    }
}
