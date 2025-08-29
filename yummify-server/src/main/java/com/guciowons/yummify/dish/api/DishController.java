package com.guciowons.yummify.dish.api;

import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import com.guciowons.yummify.dish.DishDTO;
import com.guciowons.yummify.dish.logic.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("dishes")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @PostMapping
    public ResponseEntity<DishDTO<TranslatedStringDTO>> create(@RequestBody DishDTO<TranslatedStringDTO> dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dishService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<DishDTO<String>>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dishService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<DishDTO<String>> getById(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dishService.getById(id));
    }
}
