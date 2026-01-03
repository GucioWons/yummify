package com.guciowons.yummify.menu.application.dto;

import com.guciowons.yummify.common.core.application.dto.BaseEntityDTO;

import java.util.List;
import java.util.UUID;

public record MenuClientDTO(UUID id, List<MenuSectionClientDTO> sections) implements BaseEntityDTO {
}
