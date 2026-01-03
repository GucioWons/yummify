package com.guciowons.yummify.menu.application.dto;

import com.guciowons.yummify.common.core.application.dto.PositionedDTO;

import java.util.List;
import java.util.UUID;

public record MenuSectionClientDTO(
        UUID id,
        Integer position,
        String name,
        List<MenuEntryDTO> entries
) implements PositionedDTO {
}
