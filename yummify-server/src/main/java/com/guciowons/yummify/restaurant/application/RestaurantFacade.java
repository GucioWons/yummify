package com.guciowons.yummify.restaurant.application;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.restaurant.application.model.CreateRestaurantCommand;
import com.guciowons.yummify.restaurant.application.model.GetRestaurantCommand;
import com.guciowons.yummify.restaurant.application.model.RestaurantOwner;
import com.guciowons.yummify.restaurant.application.model.UpdateRestaurantCommand;
import com.guciowons.yummify.restaurant.application.model.mapper.RestaurantCommandMapper;
import com.guciowons.yummify.restaurant.application.port.RestaurantFacadePort;
import com.guciowons.yummify.restaurant.application.usecase.CreateRestaurantUsecase;
import com.guciowons.yummify.restaurant.application.usecase.GetRestaurantUsecase;
import com.guciowons.yummify.restaurant.application.usecase.UpdateRestaurantUsecase;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class RestaurantFacade implements RestaurantFacadePort {
    private final CreateRestaurantUsecase createRestaurantUsecase;
    private final GetRestaurantUsecase getRestaurantUsecase;
    private final UpdateRestaurantUsecase updateRestaurantUsecase;
    private final RestaurantCommandMapper restaurantCommandMapper;

    @Override
    public Restaurant create(String name, Map<String, String> description, String language, RestaurantOwner owner) {
        CreateRestaurantCommand command = restaurantCommandMapper.toCreateRestaurantCommand(name, description, language, owner);
        return createRestaurantUsecase.create(command);
    }

    @Override
    public Restaurant getById(UUID id) {
        GetRestaurantCommand command = restaurantCommandMapper.toGetRestaurantCommand(id);
        return getRestaurantUsecase.get(command);
    }

    @Override
    public Restaurant update(UUID id, String name, Map<String, String> description, String language) {
        UpdateRestaurantCommand command = restaurantCommandMapper.toUpdateRestaurantCommand(id, name, description, language);
        return updateRestaurantUsecase.update(command);
    }
}
