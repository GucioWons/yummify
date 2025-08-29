package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.dish.IngredientDTO;
import com.guciowons.yummify.dish.data.IngredientRepository;
import com.guciowons.yummify.dish.entity.Ingredient;
import com.guciowons.yummify.dish.exception.IngredientNotFoundException;
import com.guciowons.yummify.dish.mapper.IngredientMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public List<IngredientDTO<String>> getAll() {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return ingredientRepository.findAllByRestaurantId(restaurantId).stream()
                .map(ingredientMapper::mapToClientDTO)
                .toList();
    }

    public IngredientDTO<TranslatedStringDTO> getById(UUID id) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return ingredientRepository.findByIdAndRestaurantId(id, restaurantId)
                .map(ingredientMapper::mapToAdminDTO)
                .orElseThrow(() -> new IngredientNotFoundException(id));
    }

    public IngredientDTO<TranslatedStringDTO> update(UUID id, IngredientDTO<TranslatedStringDTO> dto) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return ingredientRepository.findByIdAndRestaurantId(id, restaurantId)
                .map(ingredient -> ingredientMapper.mapToAdminDTO(ingredientMapper.mapToUpdateEntity(dto, ingredient)))
                .orElseThrow(() -> new IngredientNotFoundException(id));
    }
}
