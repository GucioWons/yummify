package com.guciowons.yummify.common.temp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface RestaurantScopedRepository<Entity> extends JpaRepository<Entity, UUID> {
    List<Entity> findAllByRestaurantId(UUID restaurantId);

    Optional<Entity> findByIdAndRestaurantId(UUID id, UUID restaurantId);
}
