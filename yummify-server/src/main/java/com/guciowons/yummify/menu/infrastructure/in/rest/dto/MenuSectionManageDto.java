package com.guciowons.yummify.menu.infrastructure.in.rest.dto;

import com.guciowons.yummify.common.core.application.dto.PositionedDTO;
import com.guciowons.yummify.common.core.application.validation.CheckPositionedList;
import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.TranslatedStringDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record MenuSectionManageDto(
        UUID id,
        @NotNull Integer position,
        @NotNull TranslatedStringDTO name,
        @CheckPositionedList @Valid @NotNull @NotEmpty List<MenuEntryDto> entries
) implements PositionedDTO {
}
