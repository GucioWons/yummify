package com.guciowons.yummify.ingredient.application.usecase;

import com.guciowons.yummify.ingredient.application.dto.IngredientManageDTO;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.application.dto.mapper.IngredientMapper;
import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IngredientUpdateUsecase {
    private final IngredientGetUsecase ingredientGetUsecase;
    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    @Transactional
    public Ingredient update(UUID id, IngredientManageDTO dto, UUID restaurantId) {
        Ingredient ingredient = ingredientGetUsecase.getById(id, restaurantId);
        ingredient = ingredientMapper.mapToUpdateEntity(dto, ingredient);

        return ingredientRepository.save(ingredient);
    }
}
