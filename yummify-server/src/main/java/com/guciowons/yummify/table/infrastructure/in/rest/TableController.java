package com.guciowons.yummify.table.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.SecuredByPermission;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.common.security.domain.Permission;
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
    @SecuredByPermission(Permission.TABLE_CREATE)
    public ResponseEntity<TableDto> create(
            @RequestBody TableDto dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Table tableCreated = tableFacade.create(userPrincipal.restaurantId(), dto.name(), dto.capacity());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tableMapper.mapToDto(tableCreated));
    }

    @GetMapping
    @SecuredByPermission(Permission.TABLE_READ)
    public ResponseEntity<List<TableDto>> getAll(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<TableDto> tables = tableFacade.getAll(userPrincipal.restaurantId()).stream()
                .map(tableMapper::mapToDto)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tables);
    }

    @GetMapping("{id}")
    @SecuredByPermission(Permission.TABLE_READ)
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
    @SecuredByPermission(Permission.TABLE_MODIFY)
    public ResponseEntity<TableDto> update(
            @PathVariable UUID id,
            @RequestBody TableDto dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Table updatedTable = tableFacade.update(id, userPrincipal.restaurantId(), dto.name(), dto.capacity());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tableMapper.mapToDto(updatedTable));
    }

    @PostMapping("{id}/generate-otp")
    @SecuredByPermission(Permission.TABLE_GENERATE_OTP)
    public ResponseEntity<TableOtpDto> generateOtp(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tableFacade.generateOtp(id, userPrincipal.restaurantId()));
    }
}
