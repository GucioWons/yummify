package com.guciowons.yummify.ingredient.infrastructure.adapter;

import com.guciowons.yummify.ingredient.IngredientExistencePort;
import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.restaurant.RestaurantId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientExistenceAdapter implements IngredientExistencePort {
    private final IngredientRepository ingredientRepository;

    @Override
    public List<UUID> findMissing(List<UUID> ids, RestaurantId restaurantId) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }

        List<Ingredient> found = ingredientRepository.findByIdInAndRestaurantId(ids, restaurantId);

        Set<UUID> foundIds = found.stream()
                .map(ingredient -> ingredient.getId().value())
                .collect(Collectors.toSet());

        return ids.stream()
                .filter(id -> !foundIds.contains(id))
                .toList();
    }
}
