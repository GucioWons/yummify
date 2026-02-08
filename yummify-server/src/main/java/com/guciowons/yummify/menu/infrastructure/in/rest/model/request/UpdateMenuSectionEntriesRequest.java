package com.guciowons.yummify.menu.infrastructure.in.rest.model.request;

import com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.MenuEntryDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateMenuSectionEntriesRequest(@Valid @NotNull @NotEmpty List<MenuEntryDto> entries) {
}
