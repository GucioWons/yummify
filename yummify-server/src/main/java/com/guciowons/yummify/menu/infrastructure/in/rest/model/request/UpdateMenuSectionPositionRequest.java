package com.guciowons.yummify.menu.infrastructure.in.rest.model.request;

import jakarta.validation.constraints.NotNull;

public record UpdateMenuSectionPositionRequest(@NotNull Integer position) {
}
