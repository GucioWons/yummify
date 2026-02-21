package com.guciowons.yummify.restaurant.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.SecuredByRole;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.common.security.domain.UserRole;
import com.guciowons.yummify.restaurant.application.port.RestaurantFacadePort;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.infrastructure.in.rest.dto.RestaurantClientDTO;
import com.guciowons.yummify.restaurant.infrastructure.in.rest.dto.RestaurantCreateDTO;
import com.guciowons.yummify.restaurant.infrastructure.in.rest.dto.RestaurantManageDTO;
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
    @SecuredByRole(UserRole.ADMIN)
    public ResponseEntity<RestaurantManageDTO> create(@RequestBody @Valid RestaurantCreateDTO dto) {
        Restaurant restaurant = restaurantFacade.create(
                dto.restaurant().name(),
                dto.restaurant().description().translations(),
                dto.restaurant().defaultLanguage(),
                restaurantMapper.toOwner(dto.owner())
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restaurantMapper.toManageDTO(restaurant));
    }

    @GetMapping
    public ResponseEntity<RestaurantClientDTO> getForClient(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Restaurant restaurant = restaurantFacade.getById(userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantMapper.toClientDTO(restaurant));
    }

    @GetMapping("/manage")
    @SecuredByRole(UserRole.ADMIN)
    public ResponseEntity<RestaurantManageDTO> getForAdmin(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Restaurant restaurant = restaurantFacade.getById(userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantMapper.toManageDTO(restaurant));
    }

    @PutMapping
    @SecuredByRole(UserRole.OWNER)
    public ResponseEntity<RestaurantManageDTO> update(
            @RequestBody @Valid RestaurantManageDTO dto,
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
                .body(restaurantMapper.toManageDTO(updatedRestaurant));
    }
}
