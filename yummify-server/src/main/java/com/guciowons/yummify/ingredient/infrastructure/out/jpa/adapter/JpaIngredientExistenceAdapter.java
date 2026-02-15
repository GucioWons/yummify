package com.guciowons.yummify.ingredient.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.ingredient.IngredientExistencePort;
import com.guciowons.yummify.ingredient.infrastructure.out.jpa.repository.JpaIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class JpaIngredientExistenceAdapter implements IngredientExistencePort {
    private final JpaIngredientRepository jpaIngredientRepository;

    @Override
    public List<UUID> findMissing(List<UUID> ids, UUID restaurantId) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }

        List<UUID> found = jpaIngredientRepository.findExistingIdsByRestaurantId(ids, restaurantId);

        return ids.stream()
                .filter(id -> !found.contains(id))
                .toList();
    }
}
