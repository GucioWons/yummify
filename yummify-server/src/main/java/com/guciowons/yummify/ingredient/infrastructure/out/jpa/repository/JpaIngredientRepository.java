package com.guciowons.yummify.ingredient.infrastructure.out.jpa.repository;

import com.guciowons.yummify.ingredient.infrastructure.out.jpa.entity.JpaIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaIngredientRepository extends JpaRepository<JpaIngredient, UUID> {
    Optional<JpaIngredient> findByIdAndRestaurantId(UUID id, UUID restaurantId);

    List<JpaIngredient> findAllByRestaurantId(UUID restaurantId);

    List<JpaIngredient> findByIdInAndRestaurantId(List<UUID> ids, UUID restaurantId);
}
