package com.guciowons.yummify.dish.infrastructure.in.rest;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.dish.application.DishFacade;
import com.guciowons.yummify.dish.domain.entity.value.DishImageId;
import com.guciowons.yummify.dish.infrastructure.in.rest.dto.DishClientDTO;
import com.guciowons.yummify.dish.infrastructure.in.rest.dto.DishImageUrlDTO;
import com.guciowons.yummify.dish.infrastructure.in.rest.dto.DishManageDTO;
import com.guciowons.yummify.dish.infrastructure.in.rest.dto.mapper.DishMapper;
import com.guciowons.yummify.dish.application.service.DishImageUrlProvider;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.restaurant.RestaurantId;
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
    private final DishFacade dishFacade;
    private final DishMapper dishMapper;
    private final TranslatedStringMapper translatedStringMapper;
    private final DishImageUrlProvider dishImageUrlProvider;

    @PostMapping
    public ResponseEntity<DishManageDTO> create(
            @RequestBody DishManageDTO dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Dish dishCreated = dishFacade.create(
                userPrincipal.restaurantId(),
                translatedStringMapper.toEntity(dto.name()),
                translatedStringMapper.toEntity(dto.description()),
                dto.ingredientIds()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapToManageDTO(dishCreated));
    }

    @GetMapping
    public ResponseEntity<List<DishClientDTO>> getAll(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<DishClientDTO> dishes = dishFacade.getAll(userPrincipal.restaurantId()).stream()
                .map(this::mapToClientDTO)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dishes);
    }

    @GetMapping("{id}")
    public ResponseEntity<DishManageDTO> getById(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Dish dish = dishFacade.getById(id, userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapToManageDTO(dish));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<DishManageDTO> update(
            @PathVariable UUID id,
            @RequestBody DishManageDTO dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Dish dishUpdated = dishFacade.update(
                id,
                userPrincipal.restaurantId(),
                translatedStringMapper.toEntity(dto.name()),
                translatedStringMapper.toEntity(dto.description()),
                dto.ingredientIds()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapToManageDTO(dishUpdated));
    }

    @PutMapping(value = "{id}/image", consumes = "multipart/form-data")
    public ResponseEntity<DishImageUrlDTO> updateImage(
            @PathVariable UUID id,
            @RequestParam MultipartFile image,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        DishImageId updatedImageId = dishFacade.updateImage(id, userPrincipal.restaurantId(), image);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new DishImageUrlDTO(dishImageUrlProvider.get(updatedImageId, RestaurantId.of(userPrincipal.restaurantId()))));
    }

    private DishClientDTO mapToClientDTO(Dish dish) {
        String imageUrl = dishImageUrlProvider.get(dish.getImageId(), dish.getRestaurantId());
        return dishMapper.toClientDTO(dish, imageUrl);
    }

    private DishManageDTO mapToManageDTO(Dish dish) {
        String imageUrl = dishImageUrlProvider.get(dish.getImageId(), dish.getRestaurantId());
        return dishMapper.toManageDTO(dish, imageUrl);
    }
}
