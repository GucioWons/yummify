package com.guciowons.yummify.ingredient.infrastructure.in.rest;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.ingredient.infrastructure.in.rest.dto.IngredientClientDTO;
import com.guciowons.yummify.ingredient.infrastructure.in.rest.dto.IngredientManageDTO;
import com.guciowons.yummify.ingredient.application.IngredientFacade;
import com.guciowons.yummify.ingredient.infrastructure.in.rest.dto.mapper.IngredientMapper;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
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
    private final IngredientFacade ingredientFacade;
    private final IngredientMapper ingredientMapper;
    private final TranslatedStringMapper translatedStringMapper;

    @PostMapping
    public ResponseEntity<IngredientManageDTO> create(
            @RequestBody IngredientManageDTO dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Ingredient ingredientCreated = ingredientFacade.create(
                userPrincipal.restaurantId(),
                translatedStringMapper.toEntity(dto.name())
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ingredientMapper.mapToManageDTO(ingredientCreated));
    }

    @GetMapping
    public ResponseEntity<List<IngredientClientDTO>> getAll(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<IngredientClientDTO> ingredients = ingredientFacade.getAll(userPrincipal.restaurantId()).stream()
                .map(ingredientMapper::mapToClientDTO)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ingredients);
    }

    @GetMapping("{id}")
    public ResponseEntity<IngredientManageDTO> getById(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Ingredient ingredient = ingredientFacade.getById(id, userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ingredientMapper.mapToManageDTO(ingredient));
    }

    @PutMapping("{id}")
    public ResponseEntity<IngredientManageDTO> updateById(
            @PathVariable UUID id,
            @RequestBody IngredientManageDTO dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Ingredient updatedIngredient = ingredientFacade.update(
                id,
                userPrincipal.restaurantId(),
                translatedStringMapper.toEntity(dto.name())
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ingredientMapper.mapToManageDTO(updatedIngredient));
    }
}
