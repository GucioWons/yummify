package com.guciowons.yummify.restaurant.application.usecase;

import com.guciowons.yummify.restaurant.application.dto.RestaurantManageDTO;
import com.guciowons.yummify.restaurant.application.dto.mapper.RestaurantMapper;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.infrastructure.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RestaurantUpdateUsecase {
    private final RestaurantGetUsecase restaurantGetUsecase;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public Restaurant update(UUID id, RestaurantManageDTO dto) {
        Restaurant restaurant = restaurantGetUsecase.get(id);
        restaurant = restaurantMapper.mapToUpdateEntity(dto, restaurant);

        return restaurantRepository.save(restaurant);
    }
}
