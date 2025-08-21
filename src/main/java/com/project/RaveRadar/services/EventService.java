package com.project.RaveRadar.services;

import com.project.RaveRadar.DTO.EventDTO;
import com.project.RaveRadar.exceptions.NotFoundException;
import com.project.RaveRadar.models.Event;
import com.project.RaveRadar.models.UserProfile;
import com.project.RaveRadar.payloads.EventCreationPayload;
import com.project.RaveRadar.repositories.EventRepository;
import com.project.RaveRadar.utils.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;
    private final AuthUtil authUtil;

    private String emptyToNull(String value) {
        return (value == null || value.trim().isEmpty()) ? null : value;
    }

    protected Event getEventObj(UUID eventId){
        return eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Event does not exist!"));
    }

    public ResponseEntity<EventDTO> getEvent (UUID eventId){
        return ResponseEntity.ok(new EventDTO(getEventObj(eventId)));
    }

    public ResponseEntity<List<EventDTO>> getFutureEvents(int pageNo, int pageSize){
        Instant todayStart = LocalDate.now(ZoneOffset.UTC) // or your desired zone
                .atStartOfDay()
                .toInstant(ZoneOffset.UTC);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return ResponseEntity.ok(eventRepository.findAllFutureEvents(todayStart, pageable).getContent().stream().map(EventDTO::new).toList());

    }

    public ResponseEntity<List<EventDTO>> getPastEvents(int pageNo, int pageSize){
        Instant todayStart = LocalDate.now(ZoneOffset.UTC) // or your desired zone
                .atStartOfDay()
                .toInstant(ZoneOffset.UTC);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return ResponseEntity.ok(eventRepository.findPastEvents(todayStart, pageable).getContent().stream().map(EventDTO::new).toList());
    }


    public ResponseEntity<List<EventDTO>> getAllEvents(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("startDate"));
        List<EventDTO> events = eventRepository.findAll(pageable).getContent().stream().map(EventDTO::new).toList();
        return ResponseEntity.ok(events);
    }

    @Transactional
    public ResponseEntity<EventDTO> createEvent(EventCreationPayload info) {
        Event newEvent = new Event();

        // Required fields
        // Required fields
        newEvent.setEventName(info.getEventDetails().getEventName());
        newEvent.setStartDate(info.getEventDetails().getStartDate());
        newEvent.setEventType(info.getEventDetails().getEventType());
        newEvent.setAddress(info.getLocation().getAddress());
        newEvent.setCity(info.getLocation().getCity());
        newEvent.setState(info.getLocation().getState());
        newEvent.setZipcode(info.getLocation().getZipcode());

        // Optional fields - cleaner null setting
        newEvent.setDescription(emptyToNull(info.getEventDetails().getDescription()));
        newEvent.setAvatarUrl(emptyToNull(info.getImageURLs().getAvatarUrl()));
        newEvent.setBannerUrl(emptyToNull(info.getImageURLs().getBannerUrl()));
        newEvent.setVenueName(emptyToNull(info.getLocation().getVenueName())); // 🔄 was setting event name incorrectly
        newEvent.setEndDate(Instant.parse(emptyToNull(String.valueOf(info.getEventDetails().getEndDate()))));

        // Save and return DTO
        Event savedEvent = eventRepository.save(newEvent);
        return ResponseEntity.ok(new EventDTO(savedEvent));
    }

    @Transactional
    public ResponseEntity<EventDTO> updateEvent(UUID eventId, EventCreationPayload info){
        Event newEvent = getEventObj(eventId);

        // Required fields
        newEvent.setEventName(info.getEventDetails().getEventName());
        newEvent.setStartDate(info.getEventDetails().getStartDate());
        newEvent.setEventType(info.getEventDetails().getEventType());
        newEvent.setAddress(info.getLocation().getAddress());
        newEvent.setCity(info.getLocation().getCity());
        newEvent.setState(info.getLocation().getState());
        newEvent.setZipcode(info.getLocation().getZipcode());

        // Optional fields - cleaner null setting
        newEvent.setDescription(emptyToNull(info.getEventDetails().getDescription()));
        newEvent.setAvatarUrl(emptyToNull(info.getImageURLs().getAvatarUrl()));
        newEvent.setBannerUrl(emptyToNull(info.getImageURLs().getBannerUrl()));
        newEvent.setVenueName(emptyToNull(info.getLocation().getVenueName())); // 🔄 was setting event name incorrectly
        newEvent.setEndDate(Instant.parse(emptyToNull(String.valueOf(info.getEventDetails().getEndDate()))));

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
