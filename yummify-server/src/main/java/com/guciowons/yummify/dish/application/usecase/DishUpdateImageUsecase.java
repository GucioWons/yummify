package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.dish.domain.logic.DishFinder;
import com.guciowons.yummify.dish.domain.logic.DishPersistenceService;
import com.guciowons.yummify.dish.domain.logic.DishUpdateImageService;
import com.guciowons.yummify.dish.infractructure.entity.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DishUpdateImageUsecase {
    private final DishUpdateImageService dishUpdateImageService;
    private final DishPersistenceService dishPersistenceService;
    private final DishFinder dishFinder;

    public UUID updateImage(UUID dishId, MultipartFile image) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        Dish dish = dishFinder.findById(dishId, restaurantId);

        dishUpdateImageService.updateImage(dish, image);

        return dishPersistenceService.save(dish).getImageId();
    }
}
