package com.guciowons.yummify.restaurant.domain.port;

import com.guciowons.yummify.restaurant.domain.entity.Restaurant;

import java.util.Optional;
import java.util.UUID;

public interface RestaurantRepositoryPort {
    Restaurant save(Restaurant restaurant);

    Optional<Restaurant> findById(UUID id);
}
