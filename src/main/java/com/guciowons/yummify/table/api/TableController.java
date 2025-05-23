package com.guciowons.yummify.table.api;

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
    public ResponseEntity<TableDTO> create(@RequestBody TableDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tableService.create(dto));
    }
}
