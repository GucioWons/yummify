package com.guciowons.yummify.restaurant.application.port;

import com.guciowons.yummify.restaurant.application.model.RestaurantOwner;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;

import java.util.Map;
import java.util.UUID;

public interface RestaurantFacadePort {
    Restaurant create(String name, Map<String, String> description, String language, RestaurantOwner owner);

    Restaurant getById(UUID id);

    Restaurant update(UUID id, String name, Map<String, String> description, String language);
}
