package com.guciowons.yummify.table.dto;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TableDTO implements BaseEntityDTO {
    private UUID id;
    private String name;
}
