package com.guciowons.yummify.dish.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.dish.application.port.DishFacadePort;
import com.guciowons.yummify.dish.application.service.DishImageUrlProvider;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.infrastructure.in.rest.dto.DishImageUrlDto;
import com.guciowons.yummify.dish.infrastructure.in.rest.dto.DishListDto;
import com.guciowons.yummify.dish.infrastructure.in.rest.dto.DishManageDto;
import com.guciowons.yummify.dish.infrastructure.in.rest.dto.mapper.DishMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("dishes")
@RequiredArgsConstructor
public class DishController {
    private final DishFacadePort dishFacade;
    private final DishMapper dishMapper;
    private final DishImageUrlProvider dishImageUrlProvider;

    @PostMapping
    public ResponseEntity<DishManageDto> create(
            @RequestBody DishManageDto dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Dish dishCreated = dishFacade.create(
                userPrincipal.restaurantId(),
                dto.name().translations(),
                dto.description().translations(),
                dto.ingredientIds()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapToManageDto(dishCreated));
    }

    @GetMapping
    public ResponseEntity<List<DishListDto>> getAll(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<DishListDto> dishes = dishFacade.getAll(userPrincipal.restaurantId()).stream()
                .map(dishMapper::toListDto)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dishes);
    }

    @GetMapping("{id}")
    public ResponseEntity<DishManageDto> getById(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Dish dish = dishFacade.getById(id, userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapToManageDto(dish));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<DishManageDto> update(
            @PathVariable UUID id,
            @RequestBody DishManageDto dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Dish dishUpdated = dishFacade.update(
                id,
                userPrincipal.restaurantId(),
                dto.name().translations(),
                dto.description().translations(),
                dto.ingredientIds()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapToManageDto(dishUpdated));
    }

    @PutMapping(value = "{id}/image", consumes = "multipart/form-data")
    public ResponseEntity<DishImageUrlDto> updateImage(
            @PathVariable UUID id,
            @RequestParam MultipartFile image,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Dish.ImageId updatedImageId = dishFacade.updateImage(id, userPrincipal.restaurantId(), image);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new DishImageUrlDto(dishImageUrlProvider.get(updatedImageId, Dish.RestaurantId.of(userPrincipal.restaurantId()))));
    }

    private DishManageDto mapToManageDto(Dish dish) {
        String imageUrl = dishImageUrlProvider.get(dish.getImageId(), dish.getRestaurantId());
        return dishMapper.toManageDto(dish, imageUrl);
    }
}
