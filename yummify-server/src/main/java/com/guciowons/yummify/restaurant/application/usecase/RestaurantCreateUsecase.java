package com.guciowons.yummify.restaurant.application.usecase;

import com.guciowons.yummify.auth.PublicUserCreateService;
import com.guciowons.yummify.auth.UserRequestDTO;
import com.guciowons.yummify.restaurant.application.dto.RestaurantCreateDTO;
import com.guciowons.yummify.restaurant.application.mapper.RestaurantMapper;
import com.guciowons.yummify.restaurant.domain.port.RestaurantRepositoryPort;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RestaurantCreateUsecase {
    private final RestaurantRepositoryPort restaurantRepositoryPort;
    private final RestaurantMapper restaurantMapper;
    private final PublicUserCreateService userCreateService;

    public Restaurant create(RestaurantCreateDTO dto) {
        Restaurant restaurant = restaurantMapper.mapToSaveEntity(dto.restaurant());
        restaurant = restaurantRepositoryPort.save(restaurant);

        UUID restaurantUserId = createRestaurantUser(dto.owner(), restaurant.getId());
        restaurant.setOwnerId(restaurantUserId);

        return restaurantRepositoryPort.save(restaurant);
    }

    private UUID createRestaurantUser(UserRequestDTO userRequest, UUID restaurantId) {
        userRequest.setAttributes(Map.of("restaurantId", List.of(restaurantId.toString())));
        return userCreateService.createUser(userRequest);
    }
}
