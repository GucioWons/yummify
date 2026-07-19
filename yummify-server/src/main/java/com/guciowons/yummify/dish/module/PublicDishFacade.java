package com.guciowons.yummify.dish.module;

import com.guciowons.yummify.dish.DishContract;
import com.guciowons.yummify.dish.PublicDishFacadePort;
import com.guciowons.yummify.dish.application.port.DishFacadePort;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.infrastructure.out.jpa.adapter.JpaDishExistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PublicDishFacade implements PublicDishFacadePort {
    private final DishFacadePort dishFacadePort;
    private final JpaDishExistenceAdapter jpaDishExistenceAdapter;

    @Override
    public DishContract get(UUID id, UUID restaurantId) {
        Dish dish = dishFacadePort.getById(id, restaurantId);
        return DishContract.of(dish.getName());
    }

    @Override
    public List<UUID> findMissing(List<UUID> ids, UUID restaurantId) {
        return jpaDishExistenceAdapter.findMissing(ids, restaurantId);
    }
}
