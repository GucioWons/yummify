package com.guciowons.yummify.dish.infrastructure.out.jpa;

import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import com.guciowons.yummify.dish.DishExistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DishExistenceAdapter implements DishExistencePort {
    private final DishRepository dishRepository;

    @Override
    public List<UUID> findMissing(List<UUID> ids, UUID restaurantId) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }

        List<Dish> found = dishRepository.findByIdInAndRestaurantId(ids, restaurantId);

        Set<UUID> foundIds = found.stream()
                .map(dish -> dish.getId().value())
                .collect(Collectors.toSet());

        return ids.stream()
                .filter(id -> !foundIds.contains(id))
                .toList();
    }
}
