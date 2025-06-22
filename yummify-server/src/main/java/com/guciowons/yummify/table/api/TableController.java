package com.guciowons.yummify.table.api;

import com.guciowons.yummify.common.security.aspect.SecuredByRole;
import com.guciowons.yummify.common.security.enumerated.UserRole;
import com.guciowons.yummify.table.TableCreateDTO;
import com.guciowons.yummify.table.TableDTO;
import com.guciowons.yummify.table.logic.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
