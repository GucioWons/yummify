package com.guciowons.yummify.table.application.dto;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;

import java.util.UUID;

public record TableDTO(UUID id, String name) implements BaseEntityDTO {
}
