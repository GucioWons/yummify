package com.guciowons.yummify.restaurant.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.restaurant.application.model.GetRestaurantCommand;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.domain.exception.RestaurantNotFoundException;
import com.guciowons.yummify.restaurant.domain.port.out.RestaurantRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class GetRestaurantUsecase {
    private final RestaurantRepository restaurantRepository;

    public Restaurant get(GetRestaurantCommand command) throws RestaurantNotFoundException {
        return restaurantRepository.findById(command.id())
                .orElseThrow(() -> new RestaurantNotFoundException(command.id()));
    }
}
