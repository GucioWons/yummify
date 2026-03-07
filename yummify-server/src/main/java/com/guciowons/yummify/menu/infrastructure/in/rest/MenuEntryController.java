package com.guciowons.yummify.menu.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.menu.application.entry.port.MenuEntryFacadePort;
import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.MenuEntryDto;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.mapper.MenuEntryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("menu-versions/sections")
public class MenuEntryController {
    private final MenuEntryFacadePort menuEntryFacade;
    private final MenuEntryMapper menuEntryMapper;

    @PostMapping("{sectionId}/entries")
    public ResponseEntity<MenuEntryDto> createEntry(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID sectionId,
            @RequestBody MenuEntryDto dto
    ) {
        MenuEntry createdEntry = menuEntryFacade.createEntry(
                sectionId,
                userPrincipal.restaurantId(),
                dto.dishId(),
                dto.price()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuEntryMapper.toDto(createdEntry));
    }

    @PutMapping("{sectionId}/entries/{id}")
    public ResponseEntity<MenuEntryDto> updateEntry(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable UUID sectionId,
            @PathVariable UUID id,
            @RequestBody MenuEntryDto dto
    ) {
        MenuEntry updatedEntry = menuEntryFacade.updateEntry(
                sectionId,
                id,
                userPrincipal.restaurantId(),
                dto.dishId(),
                dto.price()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(menuEntryMapper.toDto(updatedEntry));
    }
}
