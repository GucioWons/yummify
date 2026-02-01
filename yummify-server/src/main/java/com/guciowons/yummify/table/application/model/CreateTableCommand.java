package com.guciowons.yummify.table.application.model;

import com.guciowons.yummify.restaurant.RestaurantId;
import com.guciowons.yummify.table.domain.entity.value.TableName;

public record CreateTableCommand(RestaurantId restaurantId, TableName name) {
}
