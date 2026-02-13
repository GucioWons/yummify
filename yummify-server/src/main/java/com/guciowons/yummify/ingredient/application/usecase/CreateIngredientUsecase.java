package com.guciowons.yummify.ingredient.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.ingredient.application.model.CreateIngredientCommand;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class CreateIngredientUsecase {
    private final IngredientRepository ingredientRepository;

    @Transactional
    public Ingredient create(CreateIngredientCommand command) {
        Ingredient ingredient = Ingredient.create(command.restaurantId(), command.name());

        ingredientRepository.save(ingredient);

        return ingredient;
    }
}
