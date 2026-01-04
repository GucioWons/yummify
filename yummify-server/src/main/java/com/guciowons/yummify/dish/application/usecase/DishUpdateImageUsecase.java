package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.dish.application.service.DishUpdateImageService;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DishUpdateImageUsecase {
    private final DishGetUsecase dishGetUsecase;
    private final DishRepository dishRepository;
    private final DishUpdateImageService dishUpdateImageService;

    public UUID updateImage(UUID id, MultipartFile image, UUID restaurantId) {
        Dish dish = dishGetUsecase.getById(id, restaurantId);

        dishUpdateImageService.updateImage(dish, image);

        return dishRepository.save(dish).getImageId();
    }
}
