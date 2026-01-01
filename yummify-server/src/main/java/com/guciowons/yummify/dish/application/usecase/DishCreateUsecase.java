package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.dish.domain.logic.DishIngredientService;
import com.guciowons.yummify.dish.domain.logic.DishPersistenceService;
import com.guciowons.yummify.dish.application.dto.DishManageDTO;
import com.guciowons.yummify.dish.infractructure.entity.Dish;
import com.guciowons.yummify.dish.infractructure.mapper.DishMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DishCreateUsecase {
    private final DishIngredientService dishIngredientService;
    private final DishPersistenceService dishPersistenceService;
    private final DishMapper dishMapper;

    public Dish create(DishManageDTO dto) {
        Dish dish = dishMapper.mapToSaveEntity(dto);
        dish.setRestaurantId(RequestContext.get().getUser().getRestaurantId());

        List<UUID> ingredientIds = dto.ingredients() != null
                ? dto.ingredients().stream().map(BaseEntityDTO::id).toList()
                : List.of();
        dishIngredientService.replaceIngredients(dish, ingredientIds);

        return dishPersistenceService.save(dish);
    }
}
