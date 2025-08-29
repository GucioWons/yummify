package com.guciowons.yummify.dish.api;

import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import com.guciowons.yummify.dish.IngredientDTO;
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
    public ResponseEntity<IngredientDTO<TranslatedStringDTO>> create(@RequestBody IngredientDTO<TranslatedStringDTO> dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ingredientService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<IngredientDTO<String>>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ingredientService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<IngredientDTO<TranslatedStringDTO>> getById(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ingredientService.getById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<IngredientDTO<TranslatedStringDTO>> getById(@PathVariable UUID id, @RequestBody IngredientDTO<TranslatedStringDTO> dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ingredientService.update(id, dto));
    }
}
