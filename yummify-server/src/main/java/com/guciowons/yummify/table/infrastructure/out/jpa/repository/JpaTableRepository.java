package com.guciowons.yummify.table.infrastructure.out.jpa.repository;

import com.guciowons.yummify.table.infrastructure.out.jpa.entity.JpaTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaTableRepository extends JpaRepository<JpaTable, UUID> {
    Optional<JpaTable> findByIdAndRestaurantId(UUID id, UUID restaurantId);

    List<JpaTable> findAllByRestaurantId(UUID restaurantId);

    boolean existsByNameAndRestaurantId(String name, UUID restaurantId);
}
