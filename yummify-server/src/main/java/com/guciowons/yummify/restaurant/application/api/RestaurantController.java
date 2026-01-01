package com.guciowons.yummify.restaurant.application.api;

import com.guciowons.yummify.common.security.aspect.SecuredByRole;
import com.guciowons.yummify.common.security.enumerated.UserRole;
import com.guciowons.yummify.restaurant.application.facade.RestaurantFacade;
import com.guciowons.yummify.restaurant.application.dto.RestaurantClientDTO;
import com.guciowons.yummify.restaurant.application.dto.RestaurantCreateDTO;
import com.guciowons.yummify.restaurant.application.dto.RestaurantManageDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantFacade restaurantFacade;

    @PostMapping
    @SecuredByRole(UserRole.ADMIN)
    public ResponseEntity<RestaurantManageDTO> create(@RequestBody @Valid RestaurantCreateDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(restaurantFacade.create(dto));
    }

    @GetMapping
    public ResponseEntity<RestaurantClientDTO> getForClient() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantFacade.getForClient());
    }

    @GetMapping("/manage")
    @SecuredByRole(UserRole.ADMIN)
    public ResponseEntity<RestaurantManageDTO> getForAdmin() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantFacade.getForAdmin());
    }

    @PutMapping
    @SecuredByRole(UserRole.OWNER)
    public ResponseEntity<RestaurantManageDTO> update(@RequestBody @Valid RestaurantManageDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(restaurantFacade.update(dto));
    }
}
