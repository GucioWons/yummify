package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.dish.domain.logic.DishFinder;
import com.guciowons.yummify.dish.infractructure.entity.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DishGetUsecase {
    private final DishFinder dishFinder;

    public Dish getById(UUID id) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return dishFinder.findById(id, restaurantId);
    }
}
