package com.guciowons.yummify.dish.domain.ingredient.logic;

import com.guciowons.yummify.dish.exception.ingredient.IngredientsNotFoundException;
import com.guciowons.yummify.dish.infractructure.ingredient.repository.IngredientRepository;
import com.guciowons.yummify.dish.domain.ingredient.entity.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IngredientGetByIdsService {
    private final IngredientRepository ingredientRepository;

    public List<Ingredient> getByIds(List<UUID> ids, UUID restaurantId) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }

        List<Ingredient> found = ingredientRepository.findByIdInAndRestaurantId(ids, restaurantId);

        if (found.size() != ids.size()) {
            throw getIngredientsNotFoundException(ids, found);
        }

        return found;
    }

    private IngredientsNotFoundException getIngredientsNotFoundException(List<UUID> ids, List<Ingredient> found) {
        List<UUID> missingIds = ids.stream()
                .filter(id -> found.stream().noneMatch(e -> e.getId().equals(id)))
                .toList();

        return new IngredientsNotFoundException(missingIds);
    }
}
