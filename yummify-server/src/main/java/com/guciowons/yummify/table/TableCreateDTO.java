package com.guciowons.yummify.table;

import com.guciowons.yummify.auth.UserRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TableCreateDTO(
        @NotNull UUID id,
        @NotNull @NotEmpty String name,
        @Valid @NotNull UserRequestDTO user) {
}
