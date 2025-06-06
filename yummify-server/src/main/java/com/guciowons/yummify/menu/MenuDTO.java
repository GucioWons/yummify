package com.guciowons.yummify.menu;

import java.util.List;
import java.util.UUID;

public record MenuDTO(UUID id, List<MenuSectionDTO> sections) {
}
