package com.guciowons.yummify.common.security.application;

import java.util.UUID;

public record UserPrincipal(
        UUID id,
        UUID restaurantId,
        String email,
        String username,
        String firstName,
        String lastName
) {
}
