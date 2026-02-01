package com.guciowons.yummify.ingredient.infrastructure.out.jpa;

import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.domain.entity.value.IngredientId;
import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaIngredientRepository extends IngredientRepository, JpaRepository<Ingredient, IngredientId> {
}
