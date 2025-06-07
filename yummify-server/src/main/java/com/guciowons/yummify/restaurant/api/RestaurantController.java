package com.guciowons.yummify.restaurant.api;

import com.guciowons.yummify.restaurant.RestaurantCreateDTO;
import com.guciowons.yummify.restaurant.RestaurantDTO;
import com.guciowons.yummify.restaurant.logic.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody RestaurantCreateDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restaurantService.create(dto));
    }
}
