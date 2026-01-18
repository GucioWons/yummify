package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.dish.application.model.GetDishCommand;
import com.guciowons.yummify.dish.application.service.DishLookupService;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.exception.DishNotFoundException;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class DishGetUsecase {
    private final DishLookupService dishLookupService;

    public Dish getById(GetDishCommand command) throws DishNotFoundException {
        return dishLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId());
    }
}
