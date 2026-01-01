package com.guciowons.yummify.dish.application.dish.usecase;

import com.guciowons.yummify.common.RestaurantScopedService;
import com.guciowons.yummify.dish.domain.dish.logic.DishUpdateImageService;
import com.guciowons.yummify.dish.domain.dish.entity.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DishUpdateImageUsecase {
    private final RestaurantScopedService<Dish> restaurantScopedService;
    private final DishUpdateImageService dishUpdateImageService;

    public UUID updateImage(UUID id, MultipartFile image) {
        Dish dish = restaurantScopedService.findById(id);

        dishUpdateImageService.updateImage(dish, image);

        return restaurantScopedService.save(dish).getImageId();
    }
}
