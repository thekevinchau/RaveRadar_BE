package com.project.RaveRadar.payloads;

import com.project.RaveRadar.enums.EdmGenre;
import com.project.RaveRadar.enums.EventType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventFilters {
    private int pageNo;
    private int pageSize;
    private List<LocationFilter> locations;
    private List<EdmGenre> genres;
    private List<EventType> eventTypes;
    private LocalDate eventDate;
    private String sortBy;
}
