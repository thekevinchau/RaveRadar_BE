package com.project.RaveRadar.services;

import com.project.RaveRadar.DTO.EventDTO;
import com.project.RaveRadar.exceptions.NotFoundException;
import com.project.RaveRadar.models.Event;
import com.project.RaveRadar.payloads.EventPayload;
import com.project.RaveRadar.repositories.EventRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;

    private String emptyToNull(String value) {
        return (value == null || value.trim().isEmpty()) ? null : value;
    }

    private Event getEventObj(UUID eventId){
        return eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Event does not exist!"));
    }

    public ResponseEntity<EventDTO> getEvent (UUID eventId){
        return ResponseEntity.ok(new EventDTO(getEventObj(eventId)));
    }

    /*
    public ResponseEntity<EventDTO> getAllEvents(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
    }

     */

    @Transactional
    public ResponseEntity<EventDTO> createEvent(EventPayload info) {
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

    @Transactional
    public ResponseEntity<EventDTO> updateEvent(UUID eventId, EventPayload info){
        Event newEvent = getEventObj(eventId);

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

        Event savedEvent = eventRepository.save(newEvent);
        return ResponseEntity.ok(new EventDTO(savedEvent));
    }

    @Transactional
    public ResponseEntity<String> deleteEvent(UUID eventId){
        Event eventToBeDeleted = getEventObj(eventId);
        eventRepository.delete(eventToBeDeleted);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

}
