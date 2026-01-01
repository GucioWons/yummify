package com.guciowons.yummify.restaurant.application.usecase;

import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.restaurant.application.dto.RestaurantManageDTO;
import com.guciowons.yummify.restaurant.application.mapper.RestaurantMapper;
import com.guciowons.yummify.restaurant.domain.port.RestaurantRepositoryPort;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.exception.RestaurantNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RestaurantUpdateUsecase {
    private final RestaurantRepositoryPort restaurantRepositoryPort;
    private final RestaurantMapper restaurantMapper;

    public Restaurant update(RestaurantManageDTO dto) {
        UUID id = RequestContext.get().getUser().getRestaurantId();
        Restaurant restaurant = restaurantRepositoryPort.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

        Restaurant updatedRestaurant = restaurantMapper.mapToUpdateEntity(dto, restaurant);
        return restaurantRepositoryPort.save(updatedRestaurant);
    }
}
