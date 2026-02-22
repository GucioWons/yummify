package com.guciowons.yummify.restaurant.domain.port.out;

import com.guciowons.yummify.restaurant.domain.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {
    void save(Restaurant restaurant);

    Optional<Restaurant> findById(Restaurant.Id id);
}
