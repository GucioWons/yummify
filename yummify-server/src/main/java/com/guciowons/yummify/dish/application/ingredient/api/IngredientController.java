package com.guciowons.yummify.dish.application.ingredient.api;

import com.guciowons.yummify.dish.application.ingredient.dto.IngredientClientDTO;
import com.guciowons.yummify.dish.application.ingredient.dto.IngredientManageDTO;
import com.guciowons.yummify.dish.application.ingredient.facade.IngredientFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("ingredients")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientFacade ingredientFacade;

    @PostMapping
    public ResponseEntity<IngredientManageDTO> create(@RequestBody IngredientManageDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ingredientFacade.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<IngredientClientDTO>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ingredientFacade.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<IngredientManageDTO> getById(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ingredientFacade.getManageDTO(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<IngredientManageDTO> getById(@PathVariable UUID id, @RequestBody IngredientManageDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ingredientFacade.update(id, dto));
    }
}
