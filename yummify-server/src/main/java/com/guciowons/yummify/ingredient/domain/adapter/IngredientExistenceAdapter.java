package com.guciowons.yummify.ingredient.domain.adapter;

import com.guciowons.yummify.ingredient.exposed.IngredientExistencePort;
import com.guciowons.yummify.ingredient.domain.exception.IngredientsNotFoundException;
import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IngredientExistenceAdapter implements IngredientExistencePort {
    private final IngredientRepository ingredientRepository;

    @Override
    public void assertAllExist(List<UUID> ids, UUID restaurantId) {
        if (ids == null || ids.isEmpty()) {
            return;
        }

        List<Ingredient> found = ingredientRepository.findByIdInAndRestaurantId(ids, restaurantId);

        if (found.size() != ids.size()) {
            throw new IngredientsNotFoundException(getMissingIds(ids, found));
        }
    }

    private List<UUID> getMissingIds(List<UUID> ids, List<Ingredient> found) {
        return ids.stream()
                .filter(id -> found.stream().noneMatch(e -> e.getId().equals(id)))
                .toList();
    }
}
