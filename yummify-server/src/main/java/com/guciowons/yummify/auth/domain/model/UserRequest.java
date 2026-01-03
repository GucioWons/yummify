package com.guciowons.yummify.auth.domain.model;

import java.util.UUID;

public record UserRequest(
        String email,
        String username,
        String firstName,
        String lastName,
        UUID restaurantId
) {
}
