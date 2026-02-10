package com.guciowons.yummify.menu.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.menu.application.MenuSectionFacade;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.snapshot.MenuEntrySnapshot;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.MenuSectionManageDto;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.mapper.MenuEntryMapper;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.mapper.MenuSectionMapper;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.request.CreateMenuSectionRequest;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.request.UpdateMenuSectionEntriesRequest;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.request.UpdateMenuSectionNameRequest;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.request.UpdateMenuSectionPositionRequest;
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
            @RequestBody CreateMenuSectionRequest request
    ) {
        MenuSection createdSection = menuSectionFacade.create(userPrincipal.restaurantId(), request.name().translations());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(menuSectionMapper.toManageDto(createdSection));
    }

    @PatchMapping("{id}/name")
    public ResponseEntity<MenuSectionManageDto> updateName(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID id,
            @RequestBody UpdateMenuSectionNameRequest request
    ) {
        MenuSection updatedSection = menuSectionFacade.updateName(
                id,
                userPrincipal.restaurantId(),
                request.name().translations()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuSectionMapper.toManageDto(updatedSection));
    }

    @PatchMapping("{id}/position")
    public ResponseEntity<List<MenuSectionManageDto>> updatePosition(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID id,
            @RequestBody UpdateMenuSectionPositionRequest request
    ) {
        List<MenuSection> updatedSections = menuSectionFacade.updatePosition(
                id,
                userPrincipal.restaurantId(),
                request.position()
        );

        List<MenuSectionManageDto> sectionsResponse = updatedSections
                .stream()
                .map(menuSectionMapper::toManageDto)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sectionsResponse);
    }

    @PatchMapping("{id}/entries")
    public ResponseEntity<MenuSectionManageDto> updateEntries(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID id,
            @RequestBody UpdateMenuSectionEntriesRequest request
    ) {
        List<MenuEntrySnapshot> entrySnapshots = request.entries().stream()
                .map(menuEntryMapper::toSnapshot)
                .toList();

        MenuSection updatedSection = menuSectionFacade.updateEntries(id, userPrincipal.restaurantId(), entrySnapshots);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuSectionMapper.toManageDto(updatedSection));
    }
}
