package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.dish.application.model.UpdateDishCommand;
import com.guciowons.yummify.dish.application.service.DishLookupService;
import com.guciowons.yummify.dish.application.service.DishValidator;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class UpdateDishUsecase {
    private final DishLookupService dishLookupService;
    private final DishRepository dishRepository;
    private final DishValidator dishValidator;

    public Dish update(UpdateDishCommand command) {
        dishValidator.validate(command.ingredientIds(), command.restaurantId());

        Dish dish = dishLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId());
        dish.updateDetails(command.name(), command.description(), command.ingredientIds());

        dishRepository.save(dish);

        return dish;
    }
}
