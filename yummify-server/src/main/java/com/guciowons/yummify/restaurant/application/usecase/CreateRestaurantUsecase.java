package com.guciowons.yummify.restaurant.application.usecase;

import com.guciowons.yummify.auth.AuthFacadePort;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.restaurant.application.model.CreateRestaurantCommand;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.domain.port.out.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Usecase
@RequiredArgsConstructor
public class CreateRestaurantUsecase {
    private final RestaurantRepository restaurantRepository;
    private final AuthFacadePort authFacadePort;

    @Transactional
    public Restaurant create(CreateRestaurantCommand command) {
        Restaurant restaurant = Restaurant.create(command.name(), command.description(), command.defaultLanguage());

        UUID ownerId = authFacadePort.createUser(
                command.owner().email(),
                command.owner().username(),
                command.owner().firstName(),
                command.owner().lastName(),
                restaurant.getId().value(),
                true
        );

        restaurant.changeOwner(Restaurant.OwnerId.of(ownerId));

        restaurantRepository.save(restaurant);

        return restaurant;
    }
}
