package com.guciowons.yummify.restaurant.domain.port.out;

import com.guciowons.yummify.restaurant.RestaurantId;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);

    Optional<Restaurant> findById(RestaurantId id);
}
