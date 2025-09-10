package com.guciowons.yummify.common.temp;

import java.util.UUID;

public interface RestaurantScoped {
    UUID getRestaurantId();
    void setRestaurantId(UUID restaurantId);
}
