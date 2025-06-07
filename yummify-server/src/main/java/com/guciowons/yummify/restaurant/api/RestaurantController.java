package com.guciowons.yummify.restaurant.api;

import com.guciowons.yummify.restaurant.RestaurantCreateDTO;
import com.guciowons.yummify.restaurant.RestaurantDTO;
import com.guciowons.yummify.restaurant.logic.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantDTO> create(@RequestBody RestaurantCreateDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restaurantService.create(dto));
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<RestaurantDTO> getById(@PathVariable("id") UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantService.getById(id));
    }
}
