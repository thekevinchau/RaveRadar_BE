package com.project.RaveRadar.controllers;

import com.project.RaveRadar.DTO.EventDTO;
import com.project.RaveRadar.payloads.EventCreationPayload;
import com.project.RaveRadar.services.EventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody EventCreationPayload event){
        return eventService.createEvent(event);
    }

    @GetMapping("")
    public ResponseEntity<List<EventDTO>> getAllEvents(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size,
            @RequestParam(required = false) boolean isFuture){
        System.out.println(isFuture);
        if (isFuture){
            return eventService.getFutureEvents(page,size);
        }
        else{
            return eventService.getPastEvents(page, size);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable UUID id){
        return eventService.getEvent(id);
    }

    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable UUID id, @RequestBody EventCreationPayload payload){
        return eventService.updateEvent(id, payload);
    }

    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable UUID id){
        return eventService.deleteEvent(id);
    }
}
