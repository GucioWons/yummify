package com.guciowons.yummify.auth.infrastructure.framework;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("modules.auth.keycloak")
public record KeycloakProperties(
        @NotBlank String adminUsername,
        @NotBlank String adminPassword
) {
}
