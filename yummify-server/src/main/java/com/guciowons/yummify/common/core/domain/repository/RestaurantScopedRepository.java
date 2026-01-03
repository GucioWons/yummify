package com.guciowons.yummify.common.core.domain.repository;

import com.guciowons.yummify.common.core.domain.entity.RestaurantScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantScopedRepository<Entity extends RestaurantScoped> {
    Entity save(Entity entity);

    List<Entity> findAllByRestaurantId(UUID restaurantId);

    Optional<Entity> findByIdAndRestaurantId(UUID id, UUID restaurantId);

    List<Entity> findByIdInAndRestaurantId(List<UUID> ids, UUID restaurantId);
}
