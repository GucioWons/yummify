package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.dish.application.dto.DishManageDTO;
import com.guciowons.yummify.dish.application.dto.mapper.DishMapper;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import com.guciowons.yummify.dish.domain.service.DishIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DishUpdateUsecase {
    private final DishGetUsecase dishGetUsecase;
    private final DishRepository dishRepository;
    private final DishIngredientService dishIngredientService;
    private final DishMapper dishMapper;

    public Dish update(UUID id, DishManageDTO dto, UUID restaurantId) {
        Dish dish = dishGetUsecase.getById(id, restaurantId);
        dish = dishMapper.mapToUpdateEntity(dto, dish);

        dishIngredientService.replaceIngredients(dish, dto.ingredientIds());

        return dishRepository.save(dish);
    }
}
