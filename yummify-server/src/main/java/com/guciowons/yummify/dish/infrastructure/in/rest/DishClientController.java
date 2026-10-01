package com.guciowons.yummify.dish.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.SecuredByPermission;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.common.security.domain.Permission;
import com.guciowons.yummify.dish.application.DishFacade;
import com.guciowons.yummify.dish.application.service.DishImageUrlProvider;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.infrastructure.in.rest.dto.DishClientDto;
import com.guciowons.yummify.dish.infrastructure.in.rest.dto.mapper.DishMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("client/dishes")
@RequiredArgsConstructor
public class DishClientController {
    private final DishFacade dishFacade;
    private final DishMapper dishMapper;
    private final DishImageUrlProvider dishImageUrlProvider;

    @GetMapping
    @SecuredByPermission(Permission.DISH_READ)
    public ResponseEntity<List<DishClientDto>> getAll(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<DishClientDto> dishes = dishFacade.getAll(userPrincipal.restaurantId()).stream()
                .map(this::mapToClientDto)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dishes);
    }

    private DishClientDto mapToClientDto(Dish dish) {
        String imageUrl = dishImageUrlProvider.get(dish.getImageId(), dish.getRestaurantId());
        return dishMapper.toClientDto(dish, imageUrl);
    }
}
