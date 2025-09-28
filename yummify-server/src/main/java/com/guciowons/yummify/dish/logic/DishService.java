package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.core.service.TranslatableRestaurantScopedService;
import com.guciowons.yummify.dish.DishClientDTO;
import com.guciowons.yummify.dish.DishDTO;
import com.guciowons.yummify.dish.DishManageDTO;
import com.guciowons.yummify.dish.data.DishRepository;
import com.guciowons.yummify.dish.entity.Dish;
import com.guciowons.yummify.dish.exception.DishNotFoundException;
import com.guciowons.yummify.dish.mapper.DishMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DishService extends TranslatableRestaurantScopedService<Dish, DishDTO, DishManageDTO, DishClientDTO, DishRepository, DishMapper> {
    public DishService(DishRepository dishRepository, DishMapper dishMapper) {
        super(dishRepository, dishMapper);
    }

    @Override
    protected SingleApiErrorException getNotFoundException(UUID id) {
        return new DishNotFoundException(id);
    }
}
