package com.guciowons.yummify.ingredient.application.api;

import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.ingredient.application.dto.IngredientClientDTO;
import com.guciowons.yummify.ingredient.application.dto.IngredientManageDTO;
import com.guciowons.yummify.ingredient.application.IngredientFacade;
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

    @PostMapping
    public ResponseEntity<IngredientManageDTO> create(
            @RequestBody IngredientManageDTO dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ingredientFacade.create(dto, userPrincipal.restaurantId()));
    }

    @GetMapping
    public ResponseEntity<List<IngredientClientDTO>> getAll(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ingredientFacade.getAll(userPrincipal.restaurantId()));
    }

    @GetMapping("{id}")
    public ResponseEntity<IngredientManageDTO> getById(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ingredientFacade.getManageDTO(id, userPrincipal.restaurantId()));
    }

    @PutMapping("{id}")
    public ResponseEntity<IngredientManageDTO> getById(
            @PathVariable UUID id,
            @RequestBody IngredientManageDTO dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ingredientFacade.update(id, dto, userPrincipal.restaurantId()));
    }
}
