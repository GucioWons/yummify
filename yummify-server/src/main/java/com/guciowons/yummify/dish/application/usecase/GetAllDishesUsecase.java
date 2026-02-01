package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.dish.application.model.GetAllDishesCommand;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Usecase
@RequiredArgsConstructor
public class GetAllDishesUsecase {
    private final DishRepository dishRepository;

    public List<Dish> getAll(GetAllDishesCommand command) {
        return dishRepository.findAllByRestaurantId(command.restaurantId());
    }
}
