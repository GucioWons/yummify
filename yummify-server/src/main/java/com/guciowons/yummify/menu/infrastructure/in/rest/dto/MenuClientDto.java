package com.guciowons.yummify.menu.infrastructure.in.rest.dto;


import java.util.List;
import java.util.UUID;

public record MenuClientDto(UUID id, List<MenuSectionClientDto> sections) {
}
