package com.guciowons.yummify.restaurant.application;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.exception.application.handler.DomainExceptionHandler;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.restaurant.application.model.CreateRestaurantCommand;
import com.guciowons.yummify.restaurant.application.model.GetRestaurantCommand;
import com.guciowons.yummify.restaurant.application.model.RestaurantOwner;
import com.guciowons.yummify.restaurant.application.model.UpdateRestaurantCommand;
import com.guciowons.yummify.restaurant.application.model.mapper.RestaurantCommandMapper;
import com.guciowons.yummify.restaurant.application.usecase.CreateRestaurantUsecase;
import com.guciowons.yummify.restaurant.application.usecase.GetRestaurantUsecase;
import com.guciowons.yummify.restaurant.application.usecase.UpdateRestaurantUsecase;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class RestaurantFacade {
    private final CreateRestaurantUsecase createRestaurantUsecase;
    private final GetRestaurantUsecase getRestaurantUsecase;
    private final UpdateRestaurantUsecase updateRestaurantUsecase;
    private final DomainExceptionHandler restaurantDomainExceptionHandler;
    private final RestaurantCommandMapper restaurantCommandMapper;

    public Restaurant create(String name, TranslatedString description, Language language, RestaurantOwner owner) {
        CreateRestaurantCommand command = restaurantCommandMapper.toCreateRestaurantCommand(name, description, language, owner);
        return createRestaurantUsecase.create(command);
    }

    public Restaurant getById(UUID id) {
        GetRestaurantCommand command = restaurantCommandMapper.toGetRestaurantCommand(id);
        return restaurantDomainExceptionHandler.handle(() -> getRestaurantUsecase.get(command));
    }

    public Restaurant update(UUID id, String name, TranslatedString description, Language language) {
        UpdateRestaurantCommand command = restaurantCommandMapper.toUpdateRestaurantCommand(id, name, description, language);
        return restaurantDomainExceptionHandler.handle(() -> updateRestaurantUsecase.update(command));
    }
}
