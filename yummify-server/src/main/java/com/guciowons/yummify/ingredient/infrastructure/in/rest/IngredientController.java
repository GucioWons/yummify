package com.guciowons.yummify.ingredient.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.ingredient.application.port.IngredientFacadePort;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.infrastructure.in.rest.dto.IngredientClientDto;
import com.guciowons.yummify.ingredient.infrastructure.in.rest.dto.IngredientManageDto;
import com.guciowons.yummify.ingredient.infrastructure.in.rest.dto.mapper.IngredientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("ingredients")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientFacadePort ingredientFacade;
    private final IngredientMapper ingredientMapper;

    @PostMapping
    public ResponseEntity<IngredientManageDto> create(
            @RequestBody IngredientManageDto dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Ingredient ingredientCreated = ingredientFacade.create(userPrincipal.restaurantId(), dto.name().translations());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ingredientMapper.mapToManageDto(ingredientCreated));
    }

    @GetMapping
    public ResponseEntity<List<IngredientClientDto>> getAll(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<IngredientClientDto> ingredients = ingredientFacade.getAll(userPrincipal.restaurantId()).stream()
                .map(ingredientMapper::mapToClientDto)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ingredients);
    }

    @GetMapping("{id}")
    public ResponseEntity<IngredientManageDto> getById(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Ingredient ingredient = ingredientFacade.getById(id, userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ingredientMapper.mapToManageDto(ingredient));
    }

    @PutMapping("{id}")
    public ResponseEntity<IngredientManageDto> updateById(
            @PathVariable UUID id,
            @RequestBody IngredientManageDto dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Ingredient updatedIngredient = ingredientFacade.update(
                id,
                userPrincipal.restaurantId(),
                dto.name().translations()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ingredientMapper.mapToManageDto(updatedIngredient));
    }
}
