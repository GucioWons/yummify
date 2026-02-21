package com.guciowons.yummify.table.infrastructure.in.rest;

import com.guciowons.yummify.common.security.application.SecuredByRole;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.common.security.domain.UserRole;
import com.guciowons.yummify.table.application.port.TableFacadePort;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.infrastructure.in.rest.dto.TableDTO;
import com.guciowons.yummify.table.infrastructure.in.rest.dto.TableOtpDTO;
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
    public ResponseEntity<TableDTO> create(
            @RequestBody TableDTO dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Table tableCreated = tableFacade.create(userPrincipal.restaurantId(), dto.name());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tableMapper.mapToDTO(tableCreated));
    }

    @GetMapping
    public ResponseEntity<List<TableDTO>> getAll(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<TableDTO> tables = tableFacade.getAll(userPrincipal.restaurantId()).stream()
                .map(tableMapper::mapToDTO)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tables);
    }

    @GetMapping("{id}")
    public ResponseEntity<TableDTO> getById(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Table table = tableFacade.getById(id, userPrincipal.restaurantId());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tableMapper.mapToDTO(table));
    }

    @PutMapping("{id}")
    public ResponseEntity<TableDTO> update(
            @PathVariable UUID id,
            @RequestBody TableDTO dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Table updatedTable = tableFacade.update(id, userPrincipal.restaurantId(), dto.name());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tableMapper.mapToDTO(updatedTable));
    }

    @PostMapping("{id}/generate-otp")
    public ResponseEntity<TableOtpDTO> generateOtp(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tableFacade.generateOtp(id, userPrincipal.restaurantId()));
    }
}
