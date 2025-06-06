package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.dish.IngredientDTO;
import com.guciowons.yummify.dish.data.IngredientRepository;
import com.guciowons.yummify.dish.entity.Ingredient;
import com.guciowons.yummify.dish.mapper.IngredientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    public IngredientDTO create(IngredientDTO dto) {
        Ingredient ingredient = ingredientRepository.save(ingredientMapper.mapToEntity(dto));
        return ingredientMapper.mapToDTO(ingredient);
    }
}
