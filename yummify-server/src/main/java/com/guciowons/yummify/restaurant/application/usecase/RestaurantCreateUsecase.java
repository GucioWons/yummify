package com.guciowons.yummify.restaurant.application.usecase;

import com.guciowons.yummify.auth.exposed.AuthFacadePort;
import com.guciowons.yummify.restaurant.application.dto.RestaurantCreateDTO;
import com.guciowons.yummify.restaurant.application.dto.RestaurantOwnerDTO;
import com.guciowons.yummify.restaurant.application.dto.mapper.RestaurantMapper;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.infrastructure.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RestaurantCreateUsecase {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final AuthFacadePort authFacadePort;

    public Restaurant create(RestaurantCreateDTO dto) {
        Restaurant restaurant = restaurantMapper.mapToSaveEntity(dto.restaurant());
        restaurant = restaurantRepository.save(restaurant);

        UUID restaurantUserId = createRestaurantUser(dto.owner(), restaurant.getId());
        restaurant.setOwnerId(restaurantUserId);

        return restaurantRepository.save(restaurant);
    }

    private UUID createRestaurantUser(RestaurantOwnerDTO owner, UUID restaurantId) {
        return authFacadePort.createUserWithPassword(
                owner.email(),
                owner.username(),
                owner.firstName(),
                owner.lastName(),
                restaurantId
        );
    }
}
