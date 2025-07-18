package com.project.RaveRadar.DTO;



import com.project.RaveRadar.enums.EventType;
import com.project.RaveRadar.models.Event;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private UUID id;
    private String eventName;
    private String description;
    private EventType eventType;
    private String bannerUrl;
    private String avatarUrl;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String venueName;
    private Instant startDate;
    private Instant endDate;


    public EventDTO(Event event) {
        this.id = event.getId();
        this.eventName = event.getEventName();
        this.description = event.getDescription();
        this.eventType = event.getEventType();
        this.bannerUrl = event.getBannerUrl();
        this.avatarUrl = event.getAvatarUrl();
        this.address = event.getAddress();
        this.city = event.getCity();
        this.state = event.getState();
        this.zipcode = event.getZipcode();
        this.venueName = event.getVenueName();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
    }
}

