package com.guciowons.yummify.table.application.model;

import com.guciowons.yummify.table.domain.entity.Table;

public record UpdateTableCommand(Table.Id id, Table.RestaurantId restaurantId, Table.Name name, Table.Capacity capacity) {
}
