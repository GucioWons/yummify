package com.guciowons.yummify.table.application.model;

import com.guciowons.yummify.table.domain.entity.Table;

public record CreateTableCommand(Table.RestaurantId restaurantId, Table.Name name) {
}
