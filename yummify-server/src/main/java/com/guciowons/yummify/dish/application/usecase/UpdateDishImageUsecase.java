package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.common.file.domain.model.File;
import com.guciowons.yummify.dish.application.model.UpdateDishImageCommand;
import com.guciowons.yummify.dish.application.port.DishImageManagementPort;
import com.guciowons.yummify.dish.application.service.DishLookupService;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Usecase
@RequiredArgsConstructor
public class UpdateDishImageUsecase {
    private final DishLookupService dishLookupService;
    private final DishRepository dishRepository;
    private final DishImageManagementPort dishImageManagementPort;

    public File updateImage(UpdateDishImageCommand command) {
        Dish dish = dishLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId());

        upsertImage(dish, command.image());

        dishRepository.save(dish);

        return dish.getImage();
    }

    private void upsertImage(Dish dish, MultipartFile image) {
        if (image.isEmpty()) {
            if (dish.hasImage()) {
                dishImageManagementPort.delete(dish.getImage());
                dish.changeImage(null);
            }
        } else {
            if (!dish.hasImage()) {
                File file = dishImageManagementPort.create(dish.getRestaurantId(), image);
                dish.changeImage(file);
            } else {
                dishImageManagementPort.update(dish.getImage(), dish.getRestaurantId(), image);
            }
        }
    }
}
