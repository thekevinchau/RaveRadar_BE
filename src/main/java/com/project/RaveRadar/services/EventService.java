package com.project.RaveRadar.services;

import com.project.RaveRadar.DTO.EventDTO;
import com.project.RaveRadar.exceptions.BadRequestException;
import com.project.RaveRadar.exceptions.NotFoundException;
import com.project.RaveRadar.exceptions.ResourceAlreadyExistsException;
import com.project.RaveRadar.models.Event;
import com.project.RaveRadar.payloads.EventCreationPayload;
import com.project.RaveRadar.payloads.EventFilters;
import com.project.RaveRadar.repositories.EventRepository;
import com.project.RaveRadar.utils.DateUtils;
import com.project.RaveRadar.utils.EventSpecification;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ResponseEntity<List<EventDTO>> getAllEventsByCriteria(EventFilters filters){
        System.out.println("Event locations: " + filters.getLocations());
        System.out.println("Event date: " + filters.getEventDate());
        System.out.println("Event types: " + filters.getEventTypes());
        System.out.println("Genres: " + filters.getGenres());
        String sortBy = filters.getSortBy();
        Pageable pageable = PageRequest.of(filters.getPageNo(), filters.getPageSize(), Sort.by(sortBy.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC, "startDate"));
        Specification<Event> spec = (root, query, cb) -> cb.conjunction();

        if (filters.getLocations() != null && !filters.getLocations().isEmpty()){
            spec = spec.and(EventSpecification.hasCityStatePairs(filters.getLocations()));
        }

        if (filters.getEventTypes() != null && !filters.getEventTypes().isEmpty()){
            spec = spec.and(EventSpecification.hasEventType(filters.getEventTypes()));
        }

        if (filters.getEventDate() != null){
            spec = spec.and(EventSpecification.hasEventDate(filters.getEventDate()));
        }

        if (filters.getGenres() != null && !filters.getGenres().isEmpty()){
            spec = spec.and(EventSpecification.hasGenre(filters.getGenres()));
        }


        Page<Event> eventsPage = eventRepository.findAll(spec, pageable);
        List<Event> eventsContent = eventsPage.getContent();
        List <EventDTO> eventDTOs = eventsContent.stream().map(EventDTO::new).toList();
        return ResponseEntity.ok(eventDTOs);

    }

    private static Event createEventFromPayload(EventCreationPayload payload) {
        Event newEvent = new Event();
        newEvent.setEventType(payload.getEventType());
        newEvent.setDescription(payload.getDescription());
        newEvent.setName(payload.getEventName());
        newEvent.setGenre(payload.getGenre());
        newEvent.setExternalLink(payload.getExternalLink());
        newEvent.setAgeRestriction(payload.getAgeRestriction());
        newEvent.setStartDate(payload.getStartDate());
        newEvent.setEndDate(payload.getEndDate());
        return newEvent;
    }
    //Get all events close to where user is //TODO: Figure out how to use Geospatial Data in PSQL

    //Get events by searchbar //TODO: EventRepository query to search every field using LIKE and OR
    @Transactional
    public ResponseEntity<EventDTO> createEvent(EventCreationPayload payload){

        if (eventRepository.findByName(payload.getEventName()).isPresent()){
            throw new ResourceAlreadyExistsException("An event with that name already exists!");
        }
        DateUtils.validateStartBeforeEndDate(payload.getEventName(), payload.getStartDate(), payload.getEndDate());
        Event newEvent = createEventFromPayload(payload);
        Event savedEvent = eventRepository.save(newEvent);
        return ResponseEntity.ok(new EventDTO(savedEvent));
    }

    @Transactional
    public ResponseEntity<List<EventDTO>> createEventsInBulk(List<EventCreationPayload> eventsPayload){
        List<Event> eventsToBeSaved = new ArrayList<>();

        if (eventsPayload.isEmpty()){
            throw new BadRequestException("Please add at least one event!");
        }

        for (EventCreationPayload payload : eventsPayload){
            DateUtils.validateStartBeforeEndDate(payload.getEventName(), payload.getStartDate(), payload.getEndDate());
            if (eventRepository.findByName(payload.getEventName()).isPresent()){
                throw new ResourceAlreadyExistsException("An event with the name " + payload.getEventName() + " exists!");
            }
            else{
                Event newEvent = createEventFromPayload(payload);
                eventsToBeSaved.add(newEvent);
            }
        }
        List<Event> savedEvents = eventRepository.saveAll(eventsToBeSaved);
        List<EventDTO> eventDTOs = savedEvents.stream().map(EventDTO::new).toList();
        return ResponseEntity.ok(eventDTOs);
    }


}
