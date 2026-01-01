package com.guciowons.yummify.dish.application.ingredient.facade;

import com.guciowons.yummify.dish.application.ingredient.dto.IngredientClientDTO;
import com.guciowons.yummify.dish.application.ingredient.dto.IngredientManageDTO;
import com.guciowons.yummify.dish.application.ingredient.usecase.IngredientCreateUsecase;
import com.guciowons.yummify.dish.application.ingredient.usecase.IngredientGetAllUsecase;
import com.guciowons.yummify.dish.application.ingredient.usecase.IngredientGetUsecase;
import com.guciowons.yummify.dish.application.ingredient.usecase.IngredientUpdateUsecase;
import com.guciowons.yummify.dish.domain.ingredient.entity.Ingredient;
import com.guciowons.yummify.dish.application.ingredient.mapper.IngredientMapper;
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

    public IngredientManageDTO create(IngredientManageDTO dto) {
        Ingredient ingredient = ingredientCreateUsecase.create(dto);
        return ingredientMapper.mapToManageDTO(ingredient);
    }

    public List<IngredientClientDTO> getAll() {
        return ingredientGetAllUsecase.getAll().stream()
                .map(ingredientMapper::mapToClientDTO)
                .toList();
    }

    public IngredientManageDTO getManageDTO(UUID id) {
        Ingredient ingredient = ingredientGetUsecase.getById(id);
        return ingredientMapper.mapToManageDTO(ingredient);
    }

    public IngredientManageDTO update(UUID id, IngredientManageDTO dto) {
        Ingredient ingredient = ingredientUpdateUsecase.update(id, dto);
        return ingredientMapper.mapToManageDTO(ingredient);
    }
}