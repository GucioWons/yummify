package com.guciowons.yummify.table.api;

import com.guciowons.yummify.common.security.aspect.SecuredByRole;
import com.guciowons.yummify.common.security.enumerated.UserRole;
import com.guciowons.yummify.table.TableCreateDTO;
import com.guciowons.yummify.table.TableDTO;
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

    @PostMapping
    @SecuredByRole(UserRole.OWNER)
    public ResponseEntity<TableDTO> create(@RequestBody TableCreateDTO dto) {
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
}
