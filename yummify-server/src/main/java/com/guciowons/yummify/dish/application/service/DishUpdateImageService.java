package com.guciowons.yummify.dish.application.service;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.entity.value.DishImageId;
import com.guciowons.yummify.file.FileFacadePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@ApplicationService
@RequiredArgsConstructor
public class DishUpdateImageService {
    private final FileFacadePort fileFacadePort;

    public void updateImage(Dish dish, MultipartFile image) {
        if (image.isEmpty()) {
            if (dish.getImageId() != null) {
                fileFacadePort.delete(dish.getImageId().value(), dish.getRestaurantId());
                dish.changeImage(null);
            }
        } else {
            if (dish.getImageId() == null) {
                dish.changeImage(DishImageId.of(fileFacadePort.create("dish", image, dish.getRestaurantId())));
            } else {
                fileFacadePort.update(dish.getImageId().value(), "dish", image, dish.getRestaurantId());
            }
        }
    }
}
