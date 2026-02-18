package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.dish.application.model.CreateDishCommand;
import com.guciowons.yummify.dish.application.service.DishValidator;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class CreateDishUsecase {
    private final DishRepository dishRepository;
    private final DishValidator dishValidator;

    public Dish create(CreateDishCommand command) {
        dishValidator.validate(command.ingredientIds(), command.restaurantId());

        Dish dish = Dish.create(command.restaurantId(), command.name(), command.description(), command.ingredientIds());

        dishRepository.save(dish);

        return dish;
    }
}
