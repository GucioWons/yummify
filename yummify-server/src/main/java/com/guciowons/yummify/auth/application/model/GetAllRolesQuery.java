package com.guciowons.yummify.auth.application.model;

import com.guciowons.yummify.auth.domain.model.Role;

public record GetAllRolesQuery(Role.RestaurantId restaurantId) {
}
