package com.guciowons.yummify.dish.application.service;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.file.FileFacadePort;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class DishImageUrlProvider {
    private final FileFacadePort fileFacadePort;

    public String get(Dish.ImageId imageId, Dish.RestaurantId restaurantId) {
        return imageId != null
                ? fileFacadePort.getUrl(imageId.value(), restaurantId.value()).toString()
                : null;
    }
}
