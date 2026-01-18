package com.guciowons.yummify.ingredient.application;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.exception.application.handler.DomainExceptionHandler;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.ingredient.application.model.CreateIngredientCommand;
import com.guciowons.yummify.ingredient.application.model.GetAllIngredientsCommand;
import com.guciowons.yummify.ingredient.application.model.GetIngredientCommand;
import com.guciowons.yummify.ingredient.application.model.UpdateIngredientCommand;
import com.guciowons.yummify.ingredient.application.model.mapper.IngredientCommandMapper;
import com.guciowons.yummify.ingredient.application.usecase.IngredientCreateUsecase;
import com.guciowons.yummify.ingredient.application.usecase.IngredientGetAllUsecase;
import com.guciowons.yummify.ingredient.application.usecase.IngredientGetUsecase;
import com.guciowons.yummify.ingredient.application.usecase.IngredientUpdateUsecase;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class IngredientFacade {
    private final IngredientCreateUsecase ingredientCreateUsecase;
    private final IngredientGetAllUsecase ingredientGetAllUsecase;
    private final IngredientGetUsecase ingredientGetUsecase;
    private final IngredientUpdateUsecase ingredientUpdateUsecase;
    private final DomainExceptionHandler ingredientDomainExceptionHandler;
    private final IngredientCommandMapper ingredientCommandMapper;

    public Ingredient create(UUID restaurantId, TranslatedString name) {
        CreateIngredientCommand command = ingredientCommandMapper.toCreateIngredientCommand(restaurantId, name);
        return ingredientCreateUsecase.create(command);
    }

    public List<Ingredient> getAll(UUID restaurantId) {
        GetAllIngredientsCommand command = ingredientCommandMapper.toGetAllIngredientsCommand(restaurantId);
        return ingredientGetAllUsecase.getAll(command);
    }

    public Ingredient getById(UUID id, UUID restaurantId) {
        GetIngredientCommand command = ingredientCommandMapper.toGetIngredientCommand(id, restaurantId);
        return ingredientDomainExceptionHandler.handle(() -> ingredientGetUsecase.getById(command));
    }

    public Ingredient update(UUID id, UUID restaurantId, TranslatedString name) {
        UpdateIngredientCommand command = ingredientCommandMapper.toUpdateIngredientCommand(id, restaurantId, name);
        return ingredientDomainExceptionHandler.handle(() -> ingredientUpdateUsecase.update(command));
    }
}