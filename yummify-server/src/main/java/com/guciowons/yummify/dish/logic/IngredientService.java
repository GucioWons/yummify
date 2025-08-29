package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.dish.IngredientDTO;
import com.guciowons.yummify.dish.data.IngredientRepository;
import com.guciowons.yummify.dish.entity.Ingredient;
import com.guciowons.yummify.dish.mapper.IngredientMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    @Transactional
    public IngredientDTO<TranslatedStringDTO> create(IngredientDTO<TranslatedStringDTO> dto) {
        Ingredient entity = ingredientMapper.mapToEntity(dto);
        entity.setRestaurantId(RequestContext.get().getUser().getRestaurantId());
        return ingredientMapper.mapToAdminDTO(ingredientRepository.save(entity));
    }
}
