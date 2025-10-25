package com.guciowons.yummify.menu.api;

import com.guciowons.yummify.menu.dto.MenuClientDTO;
import com.guciowons.yummify.menu.dto.MenuManageDTO;
import com.guciowons.yummify.menu.logic.MenuActiveService;
import com.guciowons.yummify.menu.logic.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    private final MenuActiveService menuActiveService;

    @PostMapping
    public ResponseEntity<MenuManageDTO> create(@RequestBody MenuManageDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(menuService.create(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<MenuManageDTO> update(@PathVariable UUID id, @RequestBody MenuManageDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuService.update(id, dto));
    }

    @GetMapping("active")
    public ResponseEntity<MenuClientDTO> getActive() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuActiveService.getActive());
    }

    @PatchMapping("{id}/activate")
    public ResponseEntity<MenuManageDTO> activateMenu(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuActiveService.activate(id));
    }

    @PatchMapping("{id}/deactivate")
    public ResponseEntity<MenuManageDTO> deactivateMenu(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuActiveService.deactivate(id));
    }
}
