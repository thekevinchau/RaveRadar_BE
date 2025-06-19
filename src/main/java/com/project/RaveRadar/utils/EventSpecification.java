package com.project.RaveRadar.utils;

import com.project.RaveRadar.enums.EdmGenre;
import com.project.RaveRadar.enums.EventType;
import com.project.RaveRadar.models.Event;
import com.project.RaveRadar.payloads.LocationFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Predicates;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventSpecification {

    public static Specification<Event> hasEventType(List<EventType> eventTypes){
        return (root, query, criteriaBuilder) -> {
            if (eventTypes == null || eventTypes.isEmpty()) return null;
            return root.get("eventType").in(eventTypes);
        };
    }

    public static Specification<Event> hasCityStatePairs(List<LocationFilter> locations){
        return (root, query, criteriaBuilder) -> {
            if (locations == null || locations.isEmpty()) return null;

            List<Predicate> cityStatePredicates = new ArrayList<>();

            for (LocationFilter locFilter : locations){
                Predicate cityPredicate = criteriaBuilder.equal(root.get("address").get("city"), locFilter.getCity());
                Predicate statePredicate = criteriaBuilder.equal(root.get("address").get("state"), locFilter.getState());
                cityStatePredicates.add(criteriaBuilder.and(cityPredicate, statePredicate));
            }

            return criteriaBuilder.or(cityStatePredicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Event> hasEventDate(LocalDate date){
        return ((root, query, criteriaBuilder) -> {
            ZonedDateTime startOfDay = date.atStartOfDay(ZoneId.systemDefault());
            ZonedDateTime endOfDay = date.plusDays(1).atStartOfDay(ZoneId.systemDefault());
            return criteriaBuilder.between(root.get("startDate"), startOfDay, endOfDay);
        });
    }

    public static Specification<Event> hasGenre(List<EdmGenre> genres){
        return (root, query, criteriaBuilder) -> {
            if (genres == null || genres.isEmpty()) return null;
            return root.get("eventType").in(genres);
        };
    }

}
