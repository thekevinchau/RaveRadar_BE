package com.project.RaveRadar.controllers;

import com.project.RaveRadar.DTO.EventDTO;
import com.project.RaveRadar.payloads.EventPayload;
import com.project.RaveRadar.services.EventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody EventPayload event){
        return eventService.createEvent(event);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable UUID id){
        return eventService.getEvent(id);
    }

    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable UUID id, @RequestBody EventPayload payload){
        return eventService.updateEvent(id, payload);
    }

    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable UUID id){
        return eventService.deleteEvent(id);
    }
}
