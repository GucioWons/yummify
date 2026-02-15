package com.guciowons.yummify.ingredient.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.ingredient.application.model.UpdateIngredientCommand;
import com.guciowons.yummify.ingredient.application.service.IngredientLookupService;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class UpdateIngredientUsecase {
    private final IngredientLookupService ingredientLookupService;
    private final IngredientRepository ingredientRepository;

    @Transactional
    public Ingredient update(UpdateIngredientCommand command) {
        Ingredient ingredient = ingredientLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId());
        ingredient.updateDetails(command.name());

        ingredientRepository.save(ingredient);

        return ingredient;
    }
}
