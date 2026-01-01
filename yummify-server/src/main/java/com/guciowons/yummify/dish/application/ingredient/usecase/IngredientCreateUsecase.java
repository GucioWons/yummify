package com.guciowons.yummify.dish.application.ingredient.usecase;

import com.guciowons.yummify.common.RestaurantScopedService;
import com.guciowons.yummify.dish.application.ingredient.dto.IngredientManageDTO;
import com.guciowons.yummify.dish.domain.ingredient.entity.Ingredient;
import com.guciowons.yummify.dish.application.ingredient.mapper.IngredientMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngredientCreateUsecase {
    private final RestaurantScopedService<Ingredient> restaurantScopedService;
    private final IngredientMapper ingredientMapper;

    @Transactional
    public Ingredient create(IngredientManageDTO dto) {
        Ingredient entity = restaurantScopedService.create(ingredientMapper.mapToSaveEntity(dto));
        return restaurantScopedService.save(entity);
    }
}
