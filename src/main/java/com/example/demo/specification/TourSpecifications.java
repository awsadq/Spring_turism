package com.example.demo.specification;

import com.example.demo.model.Tour;
import org.springframework.data.jpa.domain.Specification;

public class TourSpecifications {

    public static Specification<Tour> withCountry(String country) {
        return (root, query, criteriaBuilder) ->
                (country == null || country.isEmpty()) ? null : criteriaBuilder.equal(root.get("country"), country);
    }

    public static Specification<Tour> withType(String type) {
        return (root, query, criteriaBuilder) ->
                (type == null || type.isEmpty()) ? null : criteriaBuilder.equal(root.get("type"), type);
    }

    public static Specification<Tour> withDuration(Integer duration) {
        return (root, query, criteriaBuilder) ->
                (duration == null) ? null : criteriaBuilder.equal(root.get("duration"), duration);
    }

    public static Specification<Tour> withPrice(Double price) {
        return (root, query, criteriaBuilder) ->
                (price == null) ? null : criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }
}

