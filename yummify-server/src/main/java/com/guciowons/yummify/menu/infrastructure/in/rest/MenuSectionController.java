package com.guciowons.yummify.menu.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.menu.application.MenuSectionFacade;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.snapshot.MenuEntrySnapshot;
import com.guciowons.yummify.menu.infrastructure.in.rest.dto.MenuSectionManageDto;
import com.guciowons.yummify.menu.infrastructure.in.rest.dto.mapper.MenuEntryMapper;
import com.guciowons.yummify.menu.infrastructure.in.rest.dto.mapper.MenuSectionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("menu/version/section")
@RequiredArgsConstructor
public class MenuSectionController {
    private final MenuSectionFacade menuSectionFacade;
    private final MenuSectionMapper menuSectionMapper;
    private final MenuEntryMapper menuEntryMapper;

    @PostMapping
    public ResponseEntity<MenuSectionManageDto> create(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody MenuSectionManageDto dto
    ) {
        MenuSection menuSection = menuSectionFacade.create(
                userPrincipal.restaurantId(),
                dto.name().translations(),
                dto.position()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(menuSectionMapper.toManageDto(menuSection));
    }

    @PutMapping("{id}")
    public ResponseEntity<MenuSectionManageDto> update(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID id,
            @RequestBody MenuSectionManageDto dto
    ) {
        List<MenuEntrySnapshot> menuEntrySnapshots = dto.entries().stream()
                .map(menuEntryMapper::toSnapshot)
                .toList();

        MenuSection menuSection = menuSectionFacade.update(
                id,
                userPrincipal.restaurantId(),
                dto.name().translations(),
                dto.position(),
                menuEntrySnapshots
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuSectionMapper.toManageDto(menuSection));
    }
}
