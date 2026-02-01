package com.guciowons.yummify.menu.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.menu.application.MenuFacade;
import com.guciowons.yummify.menu.domain.entity.Menu;
import com.guciowons.yummify.menu.domain.entity.update.MenuData;
import com.guciowons.yummify.menu.infrastructure.in.rest.dto.MenuManageDto;
import com.guciowons.yummify.menu.infrastructure.in.rest.dto.mapper.MenuMapper;
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
    private final MenuMapper menuMapper;

    @PostMapping
    public ResponseEntity<MenuManageDto> create(
            @RequestBody MenuManageDto dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        MenuData menuData = menuMapper.toData(dto, userPrincipal.restaurantId());
        Menu menu = menuFacade.create(menuData);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(menuMapper.toManageDto(menu));
    }

    @PutMapping("{id}")
    public ResponseEntity<MenuManageDto> update(
            @PathVariable UUID id,
            @RequestBody MenuManageDto dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        MenuData menuData = menuMapper.toData(dto, userPrincipal.restaurantId());
        Menu menu = menuFacade.update(id, menuData);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuMapper.toManageDto(menu));
    }
}
