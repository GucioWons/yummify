package com.guciowons.yummify.dish.api;

import com.guciowons.yummify.dish.DishDTO;
import com.guciowons.yummify.dish.logic.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dishes")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @PostMapping
    public ResponseEntity<DishDTO> create(@RequestBody DishDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dishService.create(dto));
    }
}
