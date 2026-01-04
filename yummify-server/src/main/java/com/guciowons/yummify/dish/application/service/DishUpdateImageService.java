package com.guciowons.yummify.dish.application.service;

import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.file.exposed.FileFacadePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class DishUpdateImageService {
    private final FileFacadePort fileFacadePort;

    public void updateImage(Dish dish, MultipartFile image) {
        if (image.isEmpty()) {
            if (dish.getImageId() != null) {
                fileFacadePort.delete(dish.getImageId(), dish.getRestaurantId());
                dish.setImageId(null);
            }
        } else {
            if (dish.getImageId() == null) {
                dish.setImageId(fileFacadePort.create("dish", image, dish.getRestaurantId()));
            } else {
                fileFacadePort.update(dish.getImageId(), "dish", image, dish.getRestaurantId());
            }
        }
    }
}
