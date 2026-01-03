package com.guciowons.yummify.menu.application.dto;

import com.guciowons.yummify.common.core.application.validation.CheckPositionedList;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record MenuManageDTO(
        UUID id,
        @CheckPositionedList @Valid @NotNull @NotEmpty List<MenuSectionManageDTO> sections,
        boolean isActive
) {
}
