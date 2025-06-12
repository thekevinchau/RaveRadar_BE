package com.project.RaveRadar.repositories;

import com.project.RaveRadar.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    //Get all events by date paginated
    Page<Event> findAllByOrderByStartDateAsc(Pageable pageable);

    Page<Event> findAll(Specification<Event> spec, Pageable pageable);


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
