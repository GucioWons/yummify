package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.common.core.service.TranslatableRestaurantScopedService;
import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.dish.dto.IngredientClientDTO;
import com.guciowons.yummify.dish.dto.IngredientListDTO;
import com.guciowons.yummify.dish.dto.IngredientManageDTO;
import com.guciowons.yummify.dish.data.IngredientRepository;
import com.guciowons.yummify.dish.entity.Ingredient;
import com.guciowons.yummify.dish.exception.IngredientNotFoundException;
import com.guciowons.yummify.dish.mapper.IngredientMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IngredientService extends TranslatableRestaurantScopedService<Ingredient, IngredientManageDTO, IngredientClientDTO, IngredientListDTO> {
    public IngredientService(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {
        super(ingredientRepository, ingredientMapper);
    }

    public void validateIngredientId(UUID id) {
        getEntityById(id);
    }

    @Override
    protected SingleApiErrorException getNotFoundException(UUID id) {
        return new IngredientNotFoundException(id);
    }
}
