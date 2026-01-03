package com.guciowons.yummify.restaurant.application;

import com.guciowons.yummify.restaurant.application.dto.RestaurantClientDTO;
import com.guciowons.yummify.restaurant.application.dto.RestaurantCreateDTO;
import com.guciowons.yummify.restaurant.application.dto.RestaurantManageDTO;
import com.guciowons.yummify.restaurant.application.dto.mapper.RestaurantMapper;
import com.guciowons.yummify.restaurant.application.usecase.RestaurantCreateUsecase;
import com.guciowons.yummify.restaurant.application.usecase.RestaurantGetUsecase;
import com.guciowons.yummify.restaurant.application.usecase.RestaurantUpdateUsecase;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RestaurantFacade {
    private final RestaurantCreateUsecase restaurantCreateUsecase;
    private final RestaurantGetUsecase restaurantGetUsecase;
    private final RestaurantUpdateUsecase restaurantUpdateUsecase;
    private final RestaurantMapper restaurantMapper;

    public RestaurantManageDTO create(RestaurantCreateDTO dto) {
        Restaurant restaurant = restaurantCreateUsecase.create(dto);
        return restaurantMapper.mapToManageDTO(restaurant);
    }

    public RestaurantClientDTO getForClient(UUID id) {
        Restaurant restaurant = restaurantGetUsecase.get(id);
        return restaurantMapper.mapToClientDTO(restaurant);
    }

    public RestaurantManageDTO getForAdmin(UUID id) {
        Restaurant restaurant = restaurantGetUsecase.get(id);
        return restaurantMapper.mapToManageDTO(restaurant);
    }

    @Transactional
    public RestaurantManageDTO update(UUID id, RestaurantManageDTO dto) {
        Restaurant updatedRestaurant = restaurantUpdateUsecase.update(id, dto);
        return restaurantMapper.mapToManageDTO(updatedRestaurant);
    }
}
