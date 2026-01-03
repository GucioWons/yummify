package com.guciowons.yummify.auth.application.dto;

import java.util.UUID;

public record UserDTO(
        UUID id,
        UUID restaurantId,
        String email,
        String username,
        String firstName,
        String lastName
) {
}
