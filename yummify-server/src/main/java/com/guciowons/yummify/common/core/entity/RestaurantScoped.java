package com.guciowons.yummify.common.core.entity;

import java.util.UUID;

public interface RestaurantScoped {
    UUID getRestaurantId();
    void setRestaurantId(UUID restaurantId);
}
