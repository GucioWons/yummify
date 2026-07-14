package com.guciowons.yummify.auth.domain.port.out;

import com.guciowons.yummify.auth.domain.model.Role;

import java.util.Optional;

public interface RoleRepository {
    void save(Role role);

    Optional<Role> findByIdAndRestaurantId(Role.Id id, Role.RestaurantId restaurantId);
}
