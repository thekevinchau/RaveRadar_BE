package com.project.RaveRadar.services;

import com.project.RaveRadar.DTO.EventDTO;
import com.project.RaveRadar.enums.EventType;
import com.project.RaveRadar.exceptions.NotFoundException;
import com.project.RaveRadar.models.Event;
import com.project.RaveRadar.repositories.EventRepository;
import com.project.RaveRadar.utils.EventSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

    //Get all events by date paginated
    public ResponseEntity<List<EventDTO>> getAllEventsAscending(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Event> eventsPage = eventRepository.findAllByOrderByStartDateAsc(pageable);
        List <Event> eventsContent = eventsPage.getContent();
        List <EventDTO> eventsDTOs = eventsContent.stream().map(EventDTO::new).toList();
        return ResponseEntity.ok(eventsDTOs);
    }

    public ResponseEntity<List<EventDTO>> getAllEventsByCriteria(int pageNo, int pageSize, String city, String state, EventType eventType, LocalDate date){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "startDate"));
        Specification<Event> spec = (root, query, cb) -> cb.conjunction();

        if (city != null && !city.isEmpty()){
            if (state != null && !state.isEmpty()){
                spec = spec.and(EventSpecification.hasLocation(state, city));
            }
        }
        if (eventType != null){
            spec = spec.and(EventSpecification.hasEventType(eventType));
        }

        if (date != null){
            spec = spec.and(EventSpecification.hasEventDate(date));
        }


        Page<Event> eventsPage = eventRepository.findAll(spec, pageable);
        List<Event> eventsContent = eventsPage.getContent();
        List <EventDTO> eventDTOs = eventsContent.stream().map(EventDTO::new).toList();
        return ResponseEntity.ok(eventDTOs);

    }

    //Get all events close to where user is //TODO: Figure out how to use Geospatial Data in PSQL

    //Get events by searchbar //TODO: EventRepository query to search every field using LIKE and OR


    /*
    Get all events based on certain criteria:
        - location
        - sorted (ascending or descending)
        - artist attendance
        - is it a festival?
     */
}
