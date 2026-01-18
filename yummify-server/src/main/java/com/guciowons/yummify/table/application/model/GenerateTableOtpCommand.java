package com.guciowons.yummify.table.application.model;

import com.guciowons.yummify.restaurant.RestaurantId;
import com.guciowons.yummify.table.domain.entity.value.TableId;

public record GenerateTableOtpCommand(TableId id, RestaurantId restaurantId) {
}
