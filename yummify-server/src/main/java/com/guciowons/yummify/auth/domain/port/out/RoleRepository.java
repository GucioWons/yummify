package com.guciowons.yummify.auth.domain.port.out;

import com.guciowons.yummify.auth.domain.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    void save(Role role);

    List<Role> findAllByRestaurantId(Role.RestaurantId restaurantId);

    Optional<Role> findByIdAndRestaurantId(Role.Id id, Role.RestaurantId restaurantId);
}
