package com.guciowons.yummify.table.api;

import com.guciowons.yummify.auth.OtpDTO;
import com.guciowons.yummify.common.security.aspect.SecuredByRole;
import com.guciowons.yummify.common.security.enumerated.UserRole;
import com.guciowons.yummify.table.TableDTO;
import com.guciowons.yummify.table.logic.TableAuthService;
import com.guciowons.yummify.table.logic.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("tables")
@RequiredArgsConstructor
public class TableController {
    private final TableService tableService;
    private final TableAuthService tableAuthService;

    @PostMapping
    @SecuredByRole(UserRole.OWNER)
    public ResponseEntity<TableDTO> create(@RequestBody TableDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tableService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<TableDTO>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tableService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<TableDTO> getById(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tableService.getById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<TableDTO> update(@PathVariable UUID id, @RequestBody TableDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tableService.update(id, dto));
    }

    @PostMapping("{id}/generate-otp")
    public ResponseEntity<OtpDTO> generateOtp(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tableAuthService.generateOtp(id));
    }
}
