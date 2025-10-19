package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.common.core.service.TranslatableRestaurantScopedService;
import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.dish.PublicDishService;
import com.guciowons.yummify.dish.data.DishRepository;
import com.guciowons.yummify.dish.DishClientDTO;
import com.guciowons.yummify.dish.dto.DishListDTO;
import com.guciowons.yummify.dish.dto.DishManageDTO;
import com.guciowons.yummify.dish.entity.Dish;
import com.guciowons.yummify.dish.entity.Ingredient;
import com.guciowons.yummify.dish.exception.DishNotFoundException;
import com.guciowons.yummify.dish.mapper.DishMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DishService extends TranslatableRestaurantScopedService<Dish, DishManageDTO, DishClientDTO, DishListDTO>
        implements PublicDishService {

    private final IngredientService ingredientService;

    public DishService(DishRepository dishRepository, DishMapper dishMapper, IngredientService ingredientService) {
        super(dishRepository, dishMapper);
        this.ingredientService = ingredientService;
    }

    @Override
    protected void afterMappingEntity(DishManageDTO dto, Dish entity) {
        if (entity.getIngredients() == null) {
            entity.setIngredients(new ArrayList<>());
        } else {
            entity.getIngredients().clear();
        }

        List<UUID> ingredientsIds = dto.getIngredients().stream().map(BaseEntityDTO::getId).toList();
        List<Ingredient> ingredients = ingredientService.getEntitiesByIds(ingredientsIds, entity.getRestaurantId());
        entity.getIngredients().addAll(ingredients);
    }

    public void validateDishId(UUID id) {
        getEntityById(id);
    }

    @Override
    protected SingleApiErrorException getNotFoundException(UUID id) {
        return new DishNotFoundException(id);
    }
}
