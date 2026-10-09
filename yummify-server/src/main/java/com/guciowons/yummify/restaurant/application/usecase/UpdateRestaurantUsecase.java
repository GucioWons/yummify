package com.guciowons.yummify.restaurant.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.restaurant.application.model.GetRestaurantCommand;
import com.guciowons.yummify.restaurant.application.model.UpdateRestaurantCommand;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.domain.exception.RestaurantNotFoundException;
import com.guciowons.yummify.restaurant.domain.port.out.RestaurantRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class UpdateRestaurantUsecase {
    private final GetRestaurantUsecase getRestaurantUsecase;
    private final RestaurantRepository restaurantRepository;

    public Restaurant update(UpdateRestaurantCommand command) throws RestaurantNotFoundException {
        Restaurant restaurant = getRestaurantUsecase.get(new GetRestaurantCommand(command.id()));

        restaurant.updateDetails(command.name(), command.description(), command.defaultLanguage());

        restaurantRepository.save(restaurant);

        return restaurant;
    }
}
