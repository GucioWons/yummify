package com.guciowons.yummify.dish.domain.logic;

import com.guciowons.yummify.dish.infractructure.entity.Dish;
import com.guciowons.yummify.dish.domain.exception.DishNotFoundException;
import com.guciowons.yummify.dish.infractructure.data.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DishFinder {
    private final DishRepository dishRepository;

    public List<Dish> findAll(UUID restaurantId) {
        return dishRepository.findAllByRestaurantId(restaurantId);
    }

    public Dish findById(UUID id, UUID restaurantId) {
        return dishRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> new DishNotFoundException(id));
    }
}
