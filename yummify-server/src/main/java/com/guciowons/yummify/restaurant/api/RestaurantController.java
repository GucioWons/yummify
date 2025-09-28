package com.guciowons.yummify.restaurant.api;

import com.guciowons.yummify.common.security.aspect.SecuredByRole;
import com.guciowons.yummify.common.security.enumerated.UserRole;
import com.guciowons.yummify.restaurant.dto.RestaurantClientDTO;
import com.guciowons.yummify.restaurant.dto.RestaurantCreateDTO;
import com.guciowons.yummify.restaurant.dto.RestaurantManageDTO;
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
    public ResponseEntity<RestaurantManageDTO> create(@RequestBody @Valid RestaurantCreateDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restaurantService.create(dto));
    }

    @GetMapping
    public ResponseEntity<RestaurantClientDTO> getForClient() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantService.getForClient());
    }

    @GetMapping("/admin")
    @SecuredByRole(UserRole.ADMIN)
    public ResponseEntity<RestaurantManageDTO> getForAdmin() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantService.getForAdmin());
    }

    @PutMapping
    @SecuredByRole(UserRole.OWNER)
    public ResponseEntity<RestaurantManageDTO> update(@RequestBody @Valid RestaurantManageDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantService.update(dto));
    }
}
