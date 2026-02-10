package com.guciowons.yummify.menu.infrastructure.in.rest.model.request;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.TranslatedStringDTO;
import jakarta.validation.constraints.NotNull;

public record CreateMenuSectionRequest(@NotNull TranslatedStringDTO name) {
}
