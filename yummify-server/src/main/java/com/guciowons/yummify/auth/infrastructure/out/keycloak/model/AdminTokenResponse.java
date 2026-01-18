package com.guciowons.yummify.auth.infrastructure.out.keycloak.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AdminTokenResponse(
        @JsonProperty("access_token") String accessToken
) {
}
