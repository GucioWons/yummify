package com.guciowons.yummify.table.application.api;

import com.guciowons.yummify.common.security.application.SecuredByRole;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.common.security.domain.UserRole;
import com.guciowons.yummify.table.application.TableFacade;
import com.guciowons.yummify.table.application.dto.TableDTO;
import com.guciowons.yummify.table.application.dto.TableOtpDTO;
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
    private final TableFacade tableFacade;

    @PostMapping
    @SecuredByRole(UserRole.OWNER)
    public ResponseEntity<TableDTO> create(
            @RequestBody TableDTO dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tableFacade.create(dto, userPrincipal.restaurantId()));
    }

    @GetMapping
    public ResponseEntity<List<TableDTO>> getAll(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tableFacade.getAll(userPrincipal.restaurantId()));
    }

    @GetMapping("{id}")
    public ResponseEntity<TableDTO> getById(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tableFacade.getById(id, userPrincipal.restaurantId()));
    }

    @PutMapping("{id}")
    public ResponseEntity<TableDTO> update(
            @PathVariable UUID id,
            @RequestBody TableDTO dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tableFacade.update(id, dto, userPrincipal.restaurantId()));
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
