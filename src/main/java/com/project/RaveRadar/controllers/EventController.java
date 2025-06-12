package com.project.RaveRadar.controllers;

import com.project.RaveRadar.DTO.EventDTO;
import com.project.RaveRadar.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/all-events")
    public ResponseEntity<List<EventDTO>> getAllEventsByDateAscending(
            @RequestParam int page,
            @RequestParam int size
    ){
        return eventService.getAllEventsAscending(page, size);
    }

    @GetMapping("")
    public ResponseEntity<List<EventDTO>> getAllEventsByLocation (
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String type
    ){
        return eventService.getAllEventsByCriteria(page, size, city, state, type);
    }
}
