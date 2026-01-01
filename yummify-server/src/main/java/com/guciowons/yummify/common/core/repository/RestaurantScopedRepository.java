package com.guciowons.yummify.common.core.repository;

import com.guciowons.yummify.common.core.entity.BaseEntity;
import com.guciowons.yummify.common.core.entity.RestaurantScoped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface RestaurantScopedRepository<Entity extends BaseEntity & RestaurantScoped> extends JpaRepository<Entity, UUID> {
    List<Entity> findAllByRestaurantId(UUID restaurantId);

    Optional<Entity> findByIdAndRestaurantId(UUID id, UUID restaurantId);

    List<Entity> findByIdInAndRestaurantId(List<UUID> ids, UUID restaurantId);
}
