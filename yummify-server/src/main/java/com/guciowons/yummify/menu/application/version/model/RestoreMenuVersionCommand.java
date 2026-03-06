package com.guciowons.yummify.menu.application.version.model;

import com.guciowons.yummify.menu.domain.entity.MenuVersion;

public record RestoreMenuVersionCommand(MenuVersion.Id id, MenuVersion.RestaurantId restaurantId) {
}
