package com.guciowons.yummify.dish.application.dish.usecase;

import com.guciowons.yummify.common.RestaurantScopedService;
import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.dish.domain.dish.logic.DishIngredientService;
import com.guciowons.yummify.dish.application.dish.dto.DishManageDTO;
import com.guciowons.yummify.dish.domain.dish.entity.Dish;
import com.guciowons.yummify.dish.application.dish.mapper.DishMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DishUpdateUsecase {
    private final RestaurantScopedService<Dish> restaurantScopedService;
    private final DishIngredientService dishIngredientService;
    private final DishMapper dishMapper;

    public Dish update(UUID id, DishManageDTO dto) {
        Dish dish = restaurantScopedService.findById(id);
        Dish updatedDish = dishMapper.mapToUpdateEntity(dto, dish);

        List<UUID> ingredientIds = dto.ingredients() != null
                ? dto.ingredients().stream().map(BaseEntityDTO::id).toList()
                : List.of();
        dishIngredientService.replaceIngredients(dish, ingredientIds);

        return restaurantScopedService.save(updatedDish);
    }
}
