package com.guciowons.yummify.dish.application.dish.usecase;

import com.guciowons.yummify.common.RestaurantScopedService;
import com.guciowons.yummify.dish.domain.dish.entity.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DishGetAllUsecase {
    private final RestaurantScopedService<Dish> restaurantScopedService;
    public List<Dish> getAll() {
        return restaurantScopedService.findAll();
    }
}
