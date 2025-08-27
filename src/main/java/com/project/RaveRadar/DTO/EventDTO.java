package com.project.RaveRadar.DTO;



import com.project.RaveRadar.models.Event;
import com.project.RaveRadar.payloads.EventDetails;
import com.project.RaveRadar.payloads.ImageURLs;
import com.project.RaveRadar.payloads.Location;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private UUID id;
    private Location location;
    private EventDetails details;
    private ImageURLs imageUrls;


    public EventDTO(Event event) {
        this.id = event.getId();
        this.location = new Location(event.getVenueName(),event.getAddress(), event.getCity(), event.getState(), event.getZipcode());
        this.details = new EventDetails(event.getEventName(), event.getDescription(), event.getEventType(), event.getStartDate(), event.getEndDate());
        this.imageUrls = new ImageURLs(event.getBannerUrl(), event.getAvatarUrl());
    }
}

