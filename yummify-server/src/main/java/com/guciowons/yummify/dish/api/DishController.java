package com.guciowons.yummify.dish.api;

import com.guciowons.yummify.dish.dto.DishManageDTO;
import com.guciowons.yummify.dish.dto.DishListDTO;
import com.guciowons.yummify.dish.logic.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("dishes")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<DishManageDTO> create(
            @RequestPart("dto") DishManageDTO dto,
            @RequestPart("image") MultipartFile image
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dishService.create(dto, image));
    }

    @GetMapping
    public ResponseEntity<List<DishListDTO>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dishService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<DishManageDTO> getById(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dishService.getManageDTO(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<DishManageDTO> update(@PathVariable UUID id, @RequestBody DishManageDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dishService.update(id, dto));
    }
}
