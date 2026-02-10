package com.guciowons.yummify.menu.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.menu.application.MenuVersionFacade;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.MenuVersionClientDto;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.MenuVersionManageDto;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.mapper.MenuVersionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("menu/version")
@RequiredArgsConstructor
public class MenuVersionController {
    private final MenuVersionFacade menuVersionFacade;
    private final MenuVersionMapper menuVersionMapper;

    @PostMapping
    public ResponseEntity<MenuVersionManageDto> create(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        MenuVersion menuVersion = menuVersionFacade.create(userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(menuVersionMapper.toManageDto(menuVersion));
    }

    @GetMapping
    public ResponseEntity<List<MenuVersionManageDto>> getAll(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<MenuVersionManageDto> menuVersions = menuVersionFacade.getAll(userPrincipal.restaurantId()).stream()
                .map(menuVersionMapper::toManageDto)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuVersions);
    }

    @GetMapping("draft")
    public ResponseEntity<MenuVersionManageDto> getDraft(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        MenuVersion draft = menuVersionFacade.getDraft(userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuVersionMapper.toManageDto(draft));
    }

    @GetMapping("published")
    public ResponseEntity<MenuVersionClientDto> getPublished(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        MenuVersion published = menuVersionFacade.getPublished(userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuVersionMapper.toClientDto(published));
    }

    @PostMapping("publish")
    public ResponseEntity<MenuVersionClientDto> publish(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        MenuVersion published = menuVersionFacade.publish(userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuVersionMapper.toClientDto(published));
    }
}
