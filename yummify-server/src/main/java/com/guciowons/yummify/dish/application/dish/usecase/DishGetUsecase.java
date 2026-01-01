package com.guciowons.yummify.dish.application.dish.usecase;

import com.guciowons.yummify.common.RestaurantScopedService;
import com.guciowons.yummify.dish.domain.dish.entity.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DishGetUsecase {
    private final RestaurantScopedService<Dish> restaurantScopedService;

    public Dish getById(UUID id) {
        return restaurantScopedService.findById(id);
    }
}
