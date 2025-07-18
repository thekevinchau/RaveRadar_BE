package com.project.RaveRadar.services;

import com.project.RaveRadar.DTO.EventDTO;
import com.project.RaveRadar.models.Event;
import com.project.RaveRadar.payloads.EventCreation;
import com.project.RaveRadar.repositories.EventRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Data
@AllArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;

    private String emptyToNull(String value) {
        return (value == null || value.trim().isEmpty()) ? null : value;
    }

    public ResponseEntity<EventDTO> createEvent(EventCreation info) {
        Event newEvent = new Event();

        // Required fields
        newEvent.setEventName(info.getEventName());
        newEvent.setStartDate(info.getStartDate());
        newEvent.setEventType(info.getEventType());
        newEvent.setAddress(info.getAddress());
        newEvent.setCity(info.getCity());
        newEvent.setState(info.getState());
        newEvent.setZipcode(info.getZipCode());

        // Optional fields - cleaner null setting
        newEvent.setDescription(emptyToNull(info.getDescription()));
        newEvent.setAvatarUrl(emptyToNull(info.getAvatarUrl()));
        newEvent.setBannerUrl(emptyToNull(info.getBannerUrl()));
        newEvent.setVenueName(emptyToNull(info.getVenueName())); // 🔄 was setting event name incorrectly
        newEvent.setEndDate(Instant.parse(emptyToNull(String.valueOf(info.getEndDate()))));

        // Save and return DTO
        Event savedEvent = eventRepository.save(newEvent);
        return ResponseEntity.ok(new EventDTO(savedEvent));
    }

}
