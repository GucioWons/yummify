package com.guciowons.yummify.restaurant.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.restaurant.application.model.GetRestaurantCommand;
import com.guciowons.yummify.restaurant.application.service.RestaurantLookupService;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.domain.exception.RestaurantNotFoundException;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class GetRestaurantUsecase {
    private final RestaurantLookupService restaurantLookupService;

    public Restaurant get(GetRestaurantCommand command) throws RestaurantNotFoundException {
        return restaurantLookupService.getById(command.id());
    }
}
