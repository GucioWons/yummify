package com.guciowons.yummify.ingredient.application.usecase;

import com.guciowons.yummify.ingredient.application.dto.IngredientManageDTO;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.application.dto.mapper.IngredientMapper;
import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IngredientCreateUsecase {
    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    @Transactional
    public Ingredient create(IngredientManageDTO dto, UUID restaurantId) {
        Ingredient entity = ingredientMapper.mapToSaveEntity(dto, restaurantId);
        return ingredientRepository.save(entity);
    }
}
