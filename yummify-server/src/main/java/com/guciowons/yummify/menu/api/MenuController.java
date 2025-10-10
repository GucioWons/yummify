package com.guciowons.yummify.menu.api;

import com.guciowons.yummify.menu.dto.MenuManageDTO;
import com.guciowons.yummify.menu.logic.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuManageDTO> create(@RequestBody MenuManageDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(menuService.create(dto));
    }
}
