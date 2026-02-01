package com.guciowons.yummify.menu.domain.entity.update;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;

import java.util.List;
import java.util.UUID;

public record MenuSectionData(UUID id, Integer position, TranslatedString name, List<MenuEntryData> entries) {
}
