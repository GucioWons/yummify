package com.guciowons.yummify.menu.infrastructure.in.rest.model.dto;

import com.guciowons.yummify.common.core.application.dto.PositionedDTO;

import java.util.List;
import java.util.UUID;

public record MenuSectionClientDto(
        UUID id,
        Integer position,
        String name,
        List<MenuEntryDto> entries
) implements PositionedDTO {
}
