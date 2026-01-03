package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DishGetAllUsecase {
    private final DishRepository dishRepository;

    public List<Dish> getAll(UUID restaurantId) {
        return dishRepository.findAllByRestaurantId(restaurantId);
    }
}
