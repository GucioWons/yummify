package com.guciowons.yummify.dish.domain.logic;

import com.guciowons.yummify.dish.infractructure.entity.Dish;
import com.guciowons.yummify.dish.infractructure.data.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishPersistenceService {
    private final DishRepository dishRepository;

    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }
}
