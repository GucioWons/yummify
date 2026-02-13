package com.guciowons.yummify.menu.infrastructure.out.jpa.repository;

import com.guciowons.yummify.menu.infrastructure.out.jpa.entity.JpaMenuVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaMenuVersionRepository extends JpaRepository<JpaMenuVersion, UUID> {
    Optional<JpaMenuVersion> findByRestaurantIdAndStatus(UUID restaurantId, String status);

    Optional<JpaMenuVersion> findByIdAndRestaurantIdAndStatus(UUID id, UUID restaurantId, String status);

    List<JpaMenuVersion> findAllByRestaurantIdAndStatus(UUID restaurantId, String status);

    boolean existsByRestaurantId(UUID restaurantId);
}
