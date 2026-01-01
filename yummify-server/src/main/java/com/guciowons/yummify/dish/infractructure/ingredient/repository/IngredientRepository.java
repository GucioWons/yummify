package com.guciowons.yummify.dish.infractructure.ingredient.repository;

import com.guciowons.yummify.dish.domain.ingredient.entity.Ingredient;
import com.guciowons.yummify.dish.domain.ingredient.port.IngredientRepositoryPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IngredientRepository extends IngredientRepositoryPort, JpaRepository<Ingredient, UUID> {
}
