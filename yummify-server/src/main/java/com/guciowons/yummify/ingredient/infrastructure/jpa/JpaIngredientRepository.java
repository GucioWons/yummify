package com.guciowons.yummify.ingredient.infrastructure.jpa;

import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaIngredientRepository extends IngredientRepository, JpaRepository<Ingredient, UUID> {
}
