package com.guciowons.yummify.restaurant.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.SecuredByPermission;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.common.security.domain.Permission;
import com.guciowons.yummify.restaurant.application.port.RestaurantFacadePort;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.infrastructure.in.rest.dto.RestaurantClientDto;
import com.guciowons.yummify.restaurant.infrastructure.in.rest.dto.RestaurantCreateDto;
import com.guciowons.yummify.restaurant.infrastructure.in.rest.dto.RestaurantManageDto;
import com.guciowons.yummify.restaurant.infrastructure.in.rest.dto.mapper.RestaurantMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantFacadePort restaurantFacade;
    private final RestaurantMapper restaurantMapper;

    @PostMapping
    @SecuredByPermission(Permission.RESTAURANT_CREATE)
    public ResponseEntity<RestaurantManageDto> create(@RequestBody @Valid RestaurantCreateDto dto) {
        Restaurant restaurant = restaurantFacade.create(
                dto.restaurant().name(),
                dto.restaurant().description().translations(),
                dto.restaurant().defaultLanguage(),
                restaurantMapper.toOwner(dto.owner())
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restaurantMapper.toManageDto(restaurant));
    }

    @GetMapping
    public ResponseEntity<RestaurantClientDto> getForClient(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Restaurant restaurant = restaurantFacade.getById(userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantMapper.toClientDto(restaurant));
    }

    @GetMapping("/manage")
    public ResponseEntity<RestaurantManageDto> getForAdmin(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Restaurant restaurant = restaurantFacade.getById(userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantMapper.toManageDto(restaurant));
    }

    @PutMapping
    @SecuredByPermission(Permission.RESTAURANT_MODIFY)
    public ResponseEntity<RestaurantManageDto> update(
            @RequestBody @Valid RestaurantManageDto dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Restaurant updatedRestaurant = restaurantFacade.update(
                userPrincipal.restaurantId(),
                dto.name(),
                dto.description().translations(),
                dto.defaultLanguage()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantMapper.toManageDto(updatedRestaurant));
    }
}
