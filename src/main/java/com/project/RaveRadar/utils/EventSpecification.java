package com.project.RaveRadar.utils;

import com.project.RaveRadar.enums.EventType;
import com.project.RaveRadar.models.Event;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class EventSpecification {
    public static Specification<Event> hasLocation(String stateName, String cityName){
        return (root, query, criteriaBuilder) -> {
            Predicate cityPredicate = criteriaBuilder.equal(root.get("address").get("city"), cityName);
            Predicate statePredicate = criteriaBuilder.equal(root.get("address").get("state"), stateName);
            return criteriaBuilder.and(cityPredicate, statePredicate);
        };
    }

    public static Specification<Event> hasEventType(EventType type){
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("eventType"), type);
    }

    public static Specification<Event> hasEventDate(LocalDate date){
        return ((root, query, criteriaBuilder) -> {
            ZonedDateTime startOfDay = date.atStartOfDay(ZoneId.systemDefault());
            ZonedDateTime endOfDay = date.plusDays(1).atStartOfDay(ZoneId.systemDefault());
            return criteriaBuilder.between(root.get("startDate"), startOfDay, endOfDay);
        });
    }

}
