package com.project.RaveRadar.utils;

import com.project.RaveRadar.models.Event;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
public class EventSpecification {
    public static Specification<Event> hasLocation(String stateName, String cityName){
        return (root, query, criteriaBuilder) -> {
            Predicate cityPredicate = criteriaBuilder.equal(root.get("address").get("city"), cityName);
            Predicate statePredicate = criteriaBuilder.equal(root.get("address").get("state"), stateName);
            return criteriaBuilder.and(cityPredicate, statePredicate);
        };
    }

    public static Specification<Event> hasEventType(String type){
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("eventType"), type);
    }
}
