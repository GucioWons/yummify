package com.guciowons.yummify.dish.application.service;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.dish.domain.entity.value.DishImageId;
import com.guciowons.yummify.file.FileFacadePort;
import com.guciowons.yummify.restaurant.RestaurantId;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class DishImageUrlProvider {
    private final FileFacadePort fileFacadePort;

    public String get(DishImageId imageId, RestaurantId restaurantId) {
        return imageId != null
                ? fileFacadePort.getUrl(imageId.value(), restaurantId).toString()
                : null;
    }
}
