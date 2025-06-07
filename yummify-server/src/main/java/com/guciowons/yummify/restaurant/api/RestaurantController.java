package com.guciowons.yummify.restaurant.api;

import com.guciowons.yummify.common.security.aspect.SecuredByRole;
import com.guciowons.yummify.common.security.enumerated.UserRole;
import com.guciowons.yummify.restaurant.RestaurantCreateDTO;
import com.guciowons.yummify.restaurant.RestaurantDTO;
import com.guciowons.yummify.restaurant.logic.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping
    @SecuredByRole(UserRole.ADMIN)
    public ResponseEntity<RestaurantDTO> create(@RequestBody @Valid RestaurantCreateDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restaurantService.create(dto));
    }

    @GetMapping
    public ResponseEntity<RestaurantDTO> get() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantService.get());
    }

    @PutMapping
    @SecuredByRole(UserRole.OWNER)
    public ResponseEntity<RestaurantDTO> update(@RequestBody @Valid RestaurantDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantService.update(dto));
    }
}
