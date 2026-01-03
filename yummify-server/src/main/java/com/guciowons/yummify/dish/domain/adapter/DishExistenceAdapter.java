package com.guciowons.yummify.dish.domain.adapter;

import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.exception.DishesNotFoundException;
import com.guciowons.yummify.dish.domain.repository.DishRepository;
import com.guciowons.yummify.dish.exposed.DishExistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DishExistenceAdapter implements DishExistencePort {
    private final DishRepository dishRepository;

    @Override
    public void assertAllExist(List<UUID> ids, UUID restaurantId) {
        if (ids == null || ids.isEmpty()) {
            return;
        }

        List<Dish> found = dishRepository.findByIdInAndRestaurantId(ids, restaurantId);

        if (found.size() != ids.size()) {
            throw new DishesNotFoundException(getMissingIds(ids, found));
        }
    }

    private List<UUID> getMissingIds(List<UUID> ids, List<Dish> found) {
        return ids.stream()
                .filter(id -> found.stream().noneMatch(e -> e.getId().equals(id)))
                .toList();
    }
}
