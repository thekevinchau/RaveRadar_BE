package com.project.RaveRadar.services;

import com.project.RaveRadar.DTO.EventDTO;
import com.project.RaveRadar.exceptions.NotFoundException;
import com.project.RaveRadar.models.Event;
import com.project.RaveRadar.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    public ResponseEntity<EventDTO> getEventById(UUID id){
        Optional< Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isEmpty()){
            throw new NotFoundException("Event was not found");
        }
        Event event = eventOptional.get();
        return ResponseEntity.ok(new EventDTO(event));
    }
    public ResponseEntity<PagedModel<EventDTO>> getAllEventsAscending(Pageable pageable){
        Page<EventDTO> events = eventRepository.findAllByOrderByStartDateAsc(pageable).map(EventDTO::new);
        return ResponseEntity.ok(new PagedModel<>(events));
    }

    //Get all events by date paginated

    //Get all events close to a particular user

    //Get all events close to where user is

    //Get events by searchbar

    //Get all events based on users' current location

    /*
    Get all events based on certain criteria:
        - location
        - sorted (ascending or descending)
        - artist attendance
        - is it a festival?
     */


}
