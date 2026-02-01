package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.dish.application.model.UpdateDishImageCommand;
import com.guciowons.yummify.dish.application.service.DishLookupService;
import com.guciowons.yummify.dish.application.service.DishUpdateImageService;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.entity.value.DishImageId;
import com.guciowons.yummify.dish.domain.exception.DishNotFoundException;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class UpdateDishImageUsecase {
    private final DishLookupService dishLookupService;
    private final DishRepository dishRepository;
    private final DishUpdateImageService dishUpdateImageService;

    public DishImageId updateImage(UpdateDishImageCommand command) throws DishNotFoundException {
        Dish dish = dishLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId());

        dishUpdateImageService.updateImage(dish, command.image());

        return dishRepository.save(dish).getImageId();
    }
}
