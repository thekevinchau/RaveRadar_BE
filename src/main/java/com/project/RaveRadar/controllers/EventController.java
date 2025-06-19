package com.project.RaveRadar.controllers;

import com.project.RaveRadar.DTO.EventDTO;
import com.project.RaveRadar.enums.EventType;
import com.project.RaveRadar.models.Event;
import com.project.RaveRadar.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;

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

    @GetMapping("")
    public ResponseEntity<List<EventDTO>> getAllEventsByQuery (
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) EventType type,
            @RequestParam( required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate eventDate
            ){
        return eventService.getAllEventsByCriteria(page, size, city, state, type, eventDate);
    }


}
