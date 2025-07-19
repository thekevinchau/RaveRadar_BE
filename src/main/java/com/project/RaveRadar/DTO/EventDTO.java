package com.project.RaveRadar.DTO;



import com.project.RaveRadar.enums.EventType;
import com.project.RaveRadar.models.Event;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private UUID id;
    private Location location;
    private EventDetails details;
    private ImageURLs imageUrls;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class EventDetails {
        private String eventName;
        private String description;
        private EventType eventType;
        private Instant startDate;
        private Instant endDate;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ImageURLs{
        private String bannerUrl;
        private String avatarUrl;
    }


    public EventDTO(Event event) {
        this.id = event.getId();
        this.location = new Location(event.getVenueName(),event.getAddress(), event.getCity(), event.getState(), event.getZipcode());
        this.details = new EventDetails(event.getEventName(), event.getDescription(), event.getEventType(), event.getStartDate(), event.getEndDate());
        this.imageUrls = new ImageURLs(event.getBannerUrl(), event.getAvatarUrl());
    }
}

