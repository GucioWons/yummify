package com.guciowons.yummify.dish.api;

import com.guciowons.yummify.dish.dto.IngredientListDTO;
import com.guciowons.yummify.dish.dto.IngredientManageDTO;
import com.guciowons.yummify.dish.logic.IngredientService;
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
    private final IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<IngredientManageDTO> create(@RequestBody IngredientManageDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ingredientService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<IngredientListDTO>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ingredientService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<IngredientManageDTO> getById(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ingredientService.getManageDTO(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<IngredientManageDTO> getById(@PathVariable UUID id, @RequestBody IngredientManageDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ingredientService.update(id, dto));
    }
}
