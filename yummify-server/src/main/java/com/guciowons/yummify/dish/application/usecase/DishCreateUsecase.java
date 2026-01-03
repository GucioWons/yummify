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
public class DishCreateUsecase {
    private final DishRepository dishRepository;
    private final DishIngredientService dishIngredientService;
    private final DishMapper dishMapper;

    public Dish create(DishManageDTO dto, UUID restaurantId) {
        Dish dish = dishMapper.mapToSaveEntity(dto, restaurantId);

        dishIngredientService.replaceIngredients(dish, dto.ingredientIds());

        return dishRepository.save(dish);
    }
}
