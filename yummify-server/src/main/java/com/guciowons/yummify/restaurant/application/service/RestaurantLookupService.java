package com.guciowons.yummify.restaurant.application.service;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.restaurant.RestaurantId;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.domain.exception.RestaurantNotFoundException;
import com.guciowons.yummify.restaurant.domain.port.out.RestaurantRepository;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class RestaurantLookupService {
    private final RestaurantRepository restaurantRepository;

    public Restaurant getById(RestaurantId id) throws RestaurantNotFoundException {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }
}
