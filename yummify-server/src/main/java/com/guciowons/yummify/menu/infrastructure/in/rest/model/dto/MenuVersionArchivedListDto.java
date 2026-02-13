package com.guciowons.yummify.menu.infrastructure.in.rest.model.dto;

import java.time.Instant;
import java.util.UUID;

public record MenuVersionArchivedListDto(UUID id, Integer version, Instant archivedAt) {
}
