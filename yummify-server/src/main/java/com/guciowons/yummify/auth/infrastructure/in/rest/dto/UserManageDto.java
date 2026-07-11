package com.guciowons.yummify.auth.infrastructure.in.rest.dto;

import java.util.UUID;

public record UserManageDto(
        UUID id,
        String email,
        String username,
        String firstName,
        String lastName,
        UUID roleId
) {
}
