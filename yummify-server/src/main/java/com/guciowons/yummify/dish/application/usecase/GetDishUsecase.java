package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.dish.application.model.GetDishQuery;
import com.guciowons.yummify.dish.application.service.DishLookupService;
import com.guciowons.yummify.dish.domain.entity.Dish;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class GetDishUsecase {
    private final DishLookupService dishLookupService;

    public Dish getById(GetDishQuery query) {
        return dishLookupService.getByIdAndRestaurantId(query.id(), query.restaurantId());
    }
}
