package com.guciowons.yummify.dish.application.api;

import com.guciowons.yummify.dish.application.dto.DishClientDTO;
import com.guciowons.yummify.dish.application.DishFacade;
import com.guciowons.yummify.dish.application.dto.DishImageUrlDTO;
import com.guciowons.yummify.dish.application.dto.DishManageDTO;
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
    private final DishFacade dishFacade;

    @PostMapping
    public ResponseEntity<DishManageDTO> create(@RequestBody DishManageDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dishFacade.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<DishClientDTO>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dishFacade.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<DishManageDTO> getById(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dishFacade.getManageDTO(id));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<DishManageDTO> update(@PathVariable UUID id, @RequestBody DishManageDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dishFacade.update(id, dto));
    }

    @PutMapping(value = "{id}/image", consumes = "multipart/form-data")
    public ResponseEntity<DishImageUrlDTO> updateImage(
            @PathVariable UUID id,
            @RequestParam MultipartFile image
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dishFacade.updateImage(id, image));
    }
}
