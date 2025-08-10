package com.project.RaveRadar.repositories;

import com.project.RaveRadar.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    @Query("SELECT e FROM Event e WHERE e.startDate >= :date ORDER BY e.startDate ASC")
    Page<Event> findAllFutureEvents(@Param("date") Instant date, Pageable pageable);
}
