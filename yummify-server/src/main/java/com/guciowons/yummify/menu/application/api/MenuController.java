package com.guciowons.yummify.menu.application.api;

import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.menu.application.dto.MenuClientDTO;
import com.guciowons.yummify.menu.application.dto.MenuManageDTO;
import com.guciowons.yummify.menu.application.MenuFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuFacade menuFacade;

    @PostMapping
    public ResponseEntity<MenuManageDTO> create(
            @RequestBody MenuManageDTO dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(menuFacade.create(dto, userPrincipal.restaurantId()));
    }

    @PutMapping("{id}")
    public ResponseEntity<MenuManageDTO> update(
            @PathVariable UUID id,
            @RequestBody MenuManageDTO dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuFacade.update(id, dto, userPrincipal.restaurantId()));
    }

    @GetMapping("active")
    public ResponseEntity<MenuClientDTO> getActive(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuFacade.getActive(userPrincipal.restaurantId()));
    }

    @PatchMapping("{id}/activate")
    public ResponseEntity<MenuManageDTO> activateMenu(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuFacade.activate(id, userPrincipal.restaurantId()));
    }

    @PatchMapping("{id}/deactivate")
    public ResponseEntity<MenuManageDTO> deactivateMenu(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuFacade.deactivate(id, userPrincipal.restaurantId()));
    }
}
