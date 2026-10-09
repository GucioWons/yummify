package com.guciowons.yummify.restaurant.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.restaurant.application.RestaurantFacade;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.infrastructure.in.rest.dto.RestaurantClientDto;
import com.guciowons.yummify.restaurant.infrastructure.in.rest.dto.mapper.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("client/restaurants")
@RequiredArgsConstructor
public class RestaurantClientController {
    private final RestaurantFacade restaurantFacade;
    private final RestaurantMapper restaurantMapper;

    @GetMapping
    public ResponseEntity<RestaurantClientDto> getForClient(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Restaurant restaurant = restaurantFacade.getById(userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantMapper.toClientDto(restaurant));
    }
}
