package com.guciowons.yummify.table.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.SecuredByRole;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.common.security.domain.UserRole;
import com.guciowons.yummify.table.application.port.TableFacadePort;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.infrastructure.in.rest.dto.TableDto;
import com.guciowons.yummify.table.infrastructure.in.rest.dto.TableOtpDto;
import com.guciowons.yummify.table.infrastructure.in.rest.dto.mapper.TableMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("tables")
@RequiredArgsConstructor
public class TableController {
    private final TableFacadePort tableFacade;
    private final TableMapper tableMapper;

    @PostMapping
    @SecuredByRole(UserRole.OWNER)
    public ResponseEntity<TableDto> create(
            @RequestBody TableDto dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Table tableCreated = tableFacade.create(userPrincipal.restaurantId(), dto.name());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tableMapper.mapToDto(tableCreated));
    }

    @GetMapping
    public ResponseEntity<List<TableDto>> getAll(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<TableDto> tables = tableFacade.getAll(userPrincipal.restaurantId()).stream()
                .map(tableMapper::mapToDto)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tables);
    }

    @GetMapping("{id}")
    public ResponseEntity<TableDto> getById(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Table table = tableFacade.getById(id, userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tableMapper.mapToDto(table));
    }

    @PutMapping("{id}")
    public ResponseEntity<TableDto> update(
            @PathVariable UUID id,
            @RequestBody TableDto dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Table updatedTable = tableFacade.update(id, userPrincipal.restaurantId(), dto.name());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tableMapper.mapToDto(updatedTable));
    }

    @PostMapping("{id}/generate-otp")
    public ResponseEntity<TableOtpDto> generateOtp(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tableFacade.generateOtp(id, userPrincipal.restaurantId()));
    }
}
