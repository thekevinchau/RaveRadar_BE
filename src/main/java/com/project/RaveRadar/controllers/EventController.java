package com.project.RaveRadar.controllers;

import com.project.RaveRadar.DTO.EventDTO;
import com.project.RaveRadar.models.Event;
import com.project.RaveRadar.payloads.EventCreation;
import com.project.RaveRadar.services.EventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    @PostMapping("")
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody EventCreation event){
        return eventService.createEvent(event);
    }
}
