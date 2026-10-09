package com.guciowons.yummify.dish.application.port;

import com.guciowons.yummify.common.file.domain.model.File;
import com.guciowons.yummify.dish.domain.entity.Dish;
import org.springframework.web.multipart.MultipartFile;

public interface DishImageManagementPort {
    File create(Dish.RestaurantId restaurantId, MultipartFile content);

    void update(File image, Dish.RestaurantId restaurantId, MultipartFile content);

    void delete(File image);
}
