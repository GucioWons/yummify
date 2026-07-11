package com.guciowons.yummify.auth.infrastructure.in.rest.dto;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.TranslatedStringDto;

import java.util.Set;
import java.util.UUID;

public record RoleManageDto(UUID id, TranslatedStringDto name, Set<String> permissions) {
}
