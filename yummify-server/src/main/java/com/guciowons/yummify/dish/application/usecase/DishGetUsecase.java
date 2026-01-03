package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.exception.DishNotFoundException;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DishGetUsecase {
    private final DishRepository dishRepository;

    public Dish getById(UUID id, UUID restaurantId) {
        return dishRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> new DishNotFoundException(id));
    }
}
