package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.dish.IngredientClientDTO;
import com.guciowons.yummify.dish.IngredientManageDTO;
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
    public IngredientManageDTO create(IngredientManageDTO dto) {
        Ingredient entity = ingredientMapper.mapToEntity(dto);
        entity.setRestaurantId(RequestContext.get().getUser().getRestaurantId());
        return ingredientMapper.mapToManageDTO(ingredientRepository.save(entity));
    }

    public List<IngredientClientDTO> getAll() {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return ingredientRepository.findAllByRestaurantId(restaurantId).stream()
                .map(ingredientMapper::mapToClientDTO)
                .toList();
    }

    public IngredientManageDTO getById(UUID id) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return ingredientRepository.findByIdAndRestaurantId(id, restaurantId)
                .map(ingredientMapper::mapToManageDTO)
                .orElseThrow(() -> new IngredientNotFoundException(id));
    }

    @Transactional
    public IngredientManageDTO update(UUID id, IngredientManageDTO dto) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return ingredientRepository.findByIdAndRestaurantId(id, restaurantId)
                .map(ingredient -> ingredientMapper.mapToManageDTO(ingredientMapper.mapToUpdateEntity(dto, ingredient)))
                .orElseThrow(() -> new IngredientNotFoundException(id));
    }
}
