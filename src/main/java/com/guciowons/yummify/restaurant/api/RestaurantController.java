package com.guciowons.yummify.restaurant.api;

import com.guciowons.yummify.restaurant.RestaurantDTO;
import com.guciowons.yummify.restaurant.logic.RestaurantService;
import lombok.RequiredArgsConstructor;
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
    public RestaurantDTO createRestaurant(@RequestBody RestaurantDTO dto) {
        return restaurantService.create(dto);
    }
}
