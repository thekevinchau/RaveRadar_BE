package com.project.RaveRadar.controllers;

import com.project.RaveRadar.DTO.EventDTO;
import com.project.RaveRadar.DTO.EventRatingDTO;
import com.project.RaveRadar.enums.EdmGenre;
import com.project.RaveRadar.enums.EventType;
import com.project.RaveRadar.models.Event;
import com.project.RaveRadar.models.EventRating;
import com.project.RaveRadar.payloads.EventCreationPayload;
import com.project.RaveRadar.payloads.EventFilters;
import com.project.RaveRadar.payloads.EventListPayload;
import com.project.RaveRadar.payloads.RatingPayload;
import com.project.RaveRadar.services.EventRatingService;
import com.project.RaveRadar.services.EventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;
    private final EventRatingService ratingService;

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable UUID id){
        return eventService.getEventById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EventDTO>> getAllEventsByDateAscending(
            @RequestParam int page,
            @RequestParam int size
    ){
        return eventService.getAllEventsAscending(page, size);
    }

    @PostMapping("")
    public ResponseEntity<List<EventDTO>> getAllEventsByQuery (
            @RequestBody EventFilters filters
            ){
        return eventService.getAllEventsByCriteria(filters);
    }
    @PostMapping("/create")
    public ResponseEntity<EventDTO> createSingleEvent(@Valid EventCreationPayload payload){
        return eventService.createEvent(payload);
    }

    @PostMapping("/bulk-create")
    public ResponseEntity<List<EventDTO>> createEvents(@RequestBody @Valid EventListPayload payload){
        return eventService.createEventsInBulk(payload.getEventPayload());
    }


}
