package com.guciowons.yummify.dish.api;

import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import com.guciowons.yummify.dish.IngredientDTO;
import com.guciowons.yummify.dish.logic.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
