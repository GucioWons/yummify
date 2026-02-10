package com.guciowons.yummify.menu.infrastructure.in.rest.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record MenuEntryDto(
        UUID id,
        @Valid @NotNull UUID dishId,
        @Valid @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 4, fraction = 2) BigDecimal price
){
}
