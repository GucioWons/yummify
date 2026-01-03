package com.guciowons.yummify.ingredient.application;

import com.guciowons.yummify.ingredient.application.dto.IngredientClientDTO;
import com.guciowons.yummify.ingredient.application.dto.IngredientManageDTO;
import com.guciowons.yummify.ingredient.application.usecase.IngredientCreateUsecase;
import com.guciowons.yummify.ingredient.application.usecase.IngredientGetAllUsecase;
import com.guciowons.yummify.ingredient.application.usecase.IngredientGetUsecase;
import com.guciowons.yummify.ingredient.application.usecase.IngredientUpdateUsecase;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.application.dto.mapper.IngredientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IngredientFacade {
    private final IngredientCreateUsecase ingredientCreateUsecase;
    private final IngredientGetAllUsecase ingredientGetAllUsecase;
    private final IngredientGetUsecase ingredientGetUsecase;
    private final IngredientUpdateUsecase ingredientUpdateUsecase;
    private final IngredientMapper ingredientMapper;

    public IngredientManageDTO create(IngredientManageDTO dto, UUID restaurantId) {
        Ingredient ingredient = ingredientCreateUsecase.create(dto,restaurantId);
        return ingredientMapper.mapToManageDTO(ingredient);
    }

    public List<IngredientClientDTO> getAll(UUID restaurantId) {
        return ingredientGetAllUsecase.getAll(restaurantId).stream()
                .map(ingredientMapper::mapToClientDTO)
                .toList();
    }

    public IngredientManageDTO getManageDTO(UUID id, UUID restaurantId) {
        Ingredient ingredient = ingredientGetUsecase.getById(id, restaurantId);
        return ingredientMapper.mapToManageDTO(ingredient);
    }

    public IngredientManageDTO update(UUID id, IngredientManageDTO dto, UUID restaurantId) {
        Ingredient ingredient = ingredientUpdateUsecase.update(id, dto, restaurantId);
        return ingredientMapper.mapToManageDTO(ingredient);
    }
}