package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.dish.domain.logic.DishFinder;
import com.guciowons.yummify.dish.infractructure.entity.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DishGetAllUsecase {
    private final DishFinder dishFinder;

    public List<Dish> getAll() {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return dishFinder.findAll(restaurantId);
    }
}
