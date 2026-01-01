package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.dish.infractructure.data.IngredientRepository;
import com.guciowons.yummify.dish.application.dto.IngredientClientDTO;
import com.guciowons.yummify.dish.application.dto.IngredientManageDTO;
import com.guciowons.yummify.dish.infractructure.entity.Ingredient;
import com.guciowons.yummify.dish.domain.exception.IngredientNotFoundException;
import com.guciowons.yummify.dish.domain.exception.IngredientsNotFoundException;
import com.guciowons.yummify.dish.infractructure.mapper.IngredientMapper;
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
        Ingredient entity = ingredientMapper.mapToSaveEntity(dto);
        entity.setRestaurantId(RequestContext.get().getUser().getRestaurantId());
        Ingredient savedEntity = ingredientRepository.save(entity);
        return ingredientMapper.mapToManageDTO(savedEntity);
    }

    public List<IngredientClientDTO> getAll() {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return ingredientRepository.findAllByRestaurantId(restaurantId).stream()
                .map(ingredientMapper::mapToClientDTO)
                .toList();
    }

    public IngredientManageDTO getManageDTO(UUID id) {
        return ingredientMapper.mapToManageDTO(getEntityById(id));
    }

    @Transactional
    public IngredientManageDTO update(UUID id, IngredientManageDTO dto) {
        Ingredient updatedEntity = ingredientMapper.mapToUpdateEntity(dto, getEntityById(id));
        Ingredient savedEntity = ingredientRepository.save(updatedEntity);
        return ingredientMapper.mapToManageDTO(savedEntity);
    }

    public Ingredient getEntityById(UUID id) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return ingredientRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> new IngredientNotFoundException(id));
    }

    public List<Ingredient> getEntitiesByIds(List<UUID> ids, UUID restaurantId) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }

        List<Ingredient> found = ingredientRepository.findByIdInAndRestaurantId(ids, restaurantId);

        if (found.size() != ids.size()) {
            List<UUID> missingIds = ids.stream()
                    .filter(id -> found.stream().noneMatch(e -> e.getId().equals(id)))
                    .toList();

            throw new IngredientsNotFoundException(missingIds);
        }

        return found;
    }
}
