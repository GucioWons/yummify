package com.guciowons.yummify.dish.application.service;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.entity.value.DishId;
import com.guciowons.yummify.dish.domain.exception.DishNotFoundException;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import com.guciowons.yummify.restaurant.RestaurantId;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class DishLookupService {
    private final DishRepository dishRepository;

    public Dish getByIdAndRestaurantId(DishId id, RestaurantId restaurantId) throws DishNotFoundException {
        return dishRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> new DishNotFoundException(id));
    }
}
