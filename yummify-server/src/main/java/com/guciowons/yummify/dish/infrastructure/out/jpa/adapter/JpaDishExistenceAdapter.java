package com.guciowons.yummify.dish.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.dish.DishExistencePort;
import com.guciowons.yummify.dish.infrastructure.out.jpa.repository.JpaDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JpaDishExistenceAdapter implements DishExistencePort {
    private final JpaDishRepository jpaDishRepository;

    @Override
    public List<UUID> findMissing(List<UUID> ids, UUID restaurantId) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }

        Set<UUID> found = jpaDishRepository.findExistingIdsByRestaurantId(ids, restaurantId);

        return ids.stream()
                .filter(id -> !found.contains(id))
                .toList();
    }
}
