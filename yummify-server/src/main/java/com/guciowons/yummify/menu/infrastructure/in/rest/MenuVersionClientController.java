package com.guciowons.yummify.menu.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.SecuredByPermission;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.common.security.domain.Permission;
import com.guciowons.yummify.menu.application.version.port.MenuVersionFacadePort;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.MenuVersionClientDto;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.mapper.MenuVersionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("client/menu-versions")
@RequiredArgsConstructor
public class MenuVersionClientController {
    private final MenuVersionFacadePort menuVersionFacade;
    private final MenuVersionMapper menuVersionMapper;

    @GetMapping("published")
    @SecuredByPermission(Permission.MENU_READ)
    public ResponseEntity<MenuVersionClientDto> getPublished(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        MenuVersion published = menuVersionFacade.getPublished(userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuVersionMapper.toClientDto(published));
    }
}
