package com.guciowons.yummify.auth.infrastructure.in.rest.dto;

import java.util.UUID;

public record UserDto(
        UUID id,
        UUID restaurantId,
        String email,
        String username,
        String firstName,
        String lastName
) {
}
