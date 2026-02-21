package com.guciowons.yummify.ingredient.application;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.ingredient.application.model.CreateIngredientCommand;
import com.guciowons.yummify.ingredient.application.model.GetAllIngredientsQuery;
import com.guciowons.yummify.ingredient.application.model.GetIngredientQuery;
import com.guciowons.yummify.ingredient.application.model.UpdateIngredientCommand;
import com.guciowons.yummify.ingredient.application.model.mapper.IngredientCommandMapper;
import com.guciowons.yummify.ingredient.application.port.IngredientFacadePort;
import com.guciowons.yummify.ingredient.application.usecase.CreateIngredientUsecase;
import com.guciowons.yummify.ingredient.application.usecase.GetAllIngredientsUsecase;
import com.guciowons.yummify.ingredient.application.usecase.GetIngredientUsecase;
import com.guciowons.yummify.ingredient.application.usecase.UpdateIngredientUsecase;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class IngredientFacade implements IngredientFacadePort {
    private final CreateIngredientUsecase createIngredientUsecase;
    private final GetAllIngredientsUsecase getAllIngredientsUsecase;
    private final GetIngredientUsecase getIngredientUsecase;
    private final UpdateIngredientUsecase updateIngredientUsecase;
    private final IngredientCommandMapper ingredientCommandMapper;

    @Override
    public Ingredient create(UUID restaurantId, Map<String, String> name) {
        CreateIngredientCommand command = ingredientCommandMapper.toCreateIngredientCommand(restaurantId, name);
        return createIngredientUsecase.create(command);
    }

    @Override
    public List<Ingredient> getAll(UUID restaurantId) {
        GetAllIngredientsQuery query = ingredientCommandMapper.toGetAllIngredientsQuery(restaurantId);
        return getAllIngredientsUsecase.getAll(query);
    }

    @Override
    public Ingredient getById(UUID id, UUID restaurantId) {
        GetIngredientQuery query = ingredientCommandMapper.toGetIngredientQuery(id, restaurantId);
        return getIngredientUsecase.getById(query);
    }

    @Override
    public Ingredient update(UUID id, UUID restaurantId, Map<String, String> name) {
        UpdateIngredientCommand command = ingredientCommandMapper.toUpdateIngredientCommand(id, restaurantId, name);
        return updateIngredientUsecase.update(command);
    }
}