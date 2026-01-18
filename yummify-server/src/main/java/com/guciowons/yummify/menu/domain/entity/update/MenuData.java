package com.guciowons.yummify.menu.domain.entity.update;

import java.util.List;
import java.util.UUID;

public record MenuData(UUID restaurantId, List<MenuSectionData> sections) {
}
