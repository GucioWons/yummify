package com.guciowons.yummify.menu.domain.entity.update;

import java.math.BigDecimal;
import java.util.UUID;

public record MenuEntryData(UUID id, UUID dishId, Integer position, BigDecimal price) {
}
