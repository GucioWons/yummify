package com.guciowons.yummify.auth.application.model;

import com.guciowons.yummify.auth.domain.model.Role;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.security.domain.Permission;

import java.util.Set;

public record CreateRoleCommand(Role.RestaurantId restaurantId, TranslatedString name, Set<Permission> permissions) {
}
