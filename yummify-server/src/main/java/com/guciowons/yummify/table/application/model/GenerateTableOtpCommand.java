package com.guciowons.yummify.table.application.model;

import com.guciowons.yummify.table.domain.entity.Table;

public record GenerateTableOtpCommand(Table.Id id, Table.RestaurantId restaurantId) {
}
