package com.guciowons.yummify.dish.application.usecase;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.dish.domain.logic.DishFinder;
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
public class DishUpdateUsecase {
    private final DishIngredientService dishIngredientService;
    private final DishPersistenceService dishPersistenceService;
    private final DishFinder dishFinder;
    private final DishMapper dishMapper;

    public Dish update(UUID id, DishManageDTO dto) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        Dish dish = dishMapper.mapToUpdateEntity(dto, dishFinder.findById(id, restaurantId));

        List<UUID> ingredientIds = dto.ingredients() != null
                ? dto.ingredients().stream().map(BaseEntityDTO::id).toList()
                : List.of();
        dishIngredientService.replaceIngredients(dish, ingredientIds);

        return dishPersistenceService.save(dish);
    }
}
