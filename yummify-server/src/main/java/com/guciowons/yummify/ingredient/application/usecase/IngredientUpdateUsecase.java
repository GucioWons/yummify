package com.guciowons.yummify.ingredient.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.ingredient.application.model.UpdateIngredientCommand;
import com.guciowons.yummify.ingredient.application.service.IngredientLookupService;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.domain.exception.IngredientNotFoundException;
import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class IngredientUpdateUsecase {
    private final IngredientLookupService ingredientLookupService;
    private final IngredientRepository ingredientRepository;

    @Transactional
    public Ingredient update(UpdateIngredientCommand command) throws IngredientNotFoundException {
        Ingredient ingredient = ingredientLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId());
        ingredient.update(command.name());
        return ingredientRepository.save(ingredient);
    }
}
