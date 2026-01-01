package com.guciowons.yummify.dish.application.ingredient.usecase;

import com.guciowons.yummify.common.RestaurantScopedService;
import com.guciowons.yummify.dish.application.ingredient.dto.IngredientManageDTO;
import com.guciowons.yummify.dish.domain.ingredient.entity.Ingredient;
import com.guciowons.yummify.dish.application.ingredient.mapper.IngredientMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IngredientUpdateUsecase {
    private final RestaurantScopedService<Ingredient> restaurantScopedService;
    private final IngredientMapper ingredientMapper;

    @Transactional
    public Ingredient update(UUID id, IngredientManageDTO dto) {
        Ingredient updatedIngredient = ingredientMapper.mapToUpdateEntity(dto, restaurantScopedService.findById(id));
        return restaurantScopedService.save(updatedIngredient);
    }
}
