package com.guciowons.yummify.menu.application.model;

import com.guciowons.yummify.menu.domain.entity.MenuVersion;

public record GetArchivedMenuVersionQuery(MenuVersion.Id id, MenuVersion.RestaurantId restaurantId) {
}
