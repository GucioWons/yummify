package com.guciowons.yummify.auth.infrastructure.out.jpa.repository;

import com.guciowons.yummify.auth.infrastructure.out.jpa.entity.JpaRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaRoleRepository extends JpaRepository<JpaRole, UUID> {
    List<JpaRole> findAllByRestaurantId(UUID restaurantId);
    Optional<JpaRole> findByIdAndRestaurantId(UUID id, UUID restaurantId);
}
