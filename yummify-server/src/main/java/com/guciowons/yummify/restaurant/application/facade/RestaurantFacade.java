package com.guciowons.yummify.restaurant.application.facade;

import com.guciowons.yummify.restaurant.application.dto.RestaurantClientDTO;
import com.guciowons.yummify.restaurant.application.dto.RestaurantCreateDTO;
import com.guciowons.yummify.restaurant.application.dto.RestaurantManageDTO;
import com.guciowons.yummify.restaurant.application.mapper.RestaurantMapper;
import com.guciowons.yummify.restaurant.application.usecase.RestaurantCreateUsecase;
import com.guciowons.yummify.restaurant.application.usecase.RestaurantGetActiveUsecase;
import com.guciowons.yummify.restaurant.application.usecase.RestaurantUpdateUsecase;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantFacade {
    private final RestaurantCreateUsecase restaurantCreateUsecase;
    private final RestaurantGetActiveUsecase restaurantGetActiveUsecase;
    private final RestaurantUpdateUsecase restaurantUpdateUsecase;
    private final RestaurantMapper restaurantMapper;

    public RestaurantManageDTO create(RestaurantCreateDTO dto) {
        Restaurant restaurant = restaurantCreateUsecase.create(dto);
        return restaurantMapper.mapToManageDTO(restaurant);
    }

    public RestaurantClientDTO getForClient() {
        Restaurant restaurant = restaurantGetActiveUsecase.get();
        return restaurantMapper.mapToClientDTO(restaurant);
    }

    public RestaurantManageDTO getForAdmin() {
        Restaurant restaurant = restaurantGetActiveUsecase.get();
        return restaurantMapper.mapToManageDTO(restaurant);
    }

    @Transactional
    public RestaurantManageDTO update(RestaurantManageDTO dto) {
        Restaurant updatedRestaurant = restaurantUpdateUsecase.update(dto);
        return restaurantMapper.mapToManageDTO(updatedRestaurant);
    }
}
