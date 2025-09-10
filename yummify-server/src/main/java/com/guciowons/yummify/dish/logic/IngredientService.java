package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.core.service.TranslatableRestaurantScopedService;
import com.guciowons.yummify.dish.IngredientClientDTO;
import com.guciowons.yummify.dish.IngredientDTO;
import com.guciowons.yummify.dish.IngredientManageDTO;
import com.guciowons.yummify.dish.data.IngredientRepository;
import com.guciowons.yummify.dish.entity.Ingredient;
import com.guciowons.yummify.dish.exception.IngredientNotFoundException;
import com.guciowons.yummify.dish.mapper.IngredientMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IngredientService extends TranslatableRestaurantScopedService<Ingredient, IngredientDTO, IngredientManageDTO, IngredientClientDTO, IngredientRepository, IngredientMapper> {
    public IngredientService(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {
        super(ingredientRepository, ingredientMapper);
    }

    @Override
    protected SingleApiErrorException getNotFoundException(UUID id) {
        return new IngredientNotFoundException(id);
    }
}
