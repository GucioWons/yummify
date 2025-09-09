package com.guciowons.yummify.dish.data;

import com.guciowons.yummify.dish.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {
    Optional<Ingredient> findByIdAndRestaurantId(UUID id, UUID restaurantId);
    List<Ingredient> findAllByRestaurantId(UUID restaurantId);
}
