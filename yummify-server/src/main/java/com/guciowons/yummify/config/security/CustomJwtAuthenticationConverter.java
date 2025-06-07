package com.guciowons.yummify.config.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.UUID;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final KeycloakRoleConverter roleConverter = new KeycloakRoleConverter();

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        Collection<GrantedAuthority> authorities = roleConverter.convert(jwt).getAuthorities();

        UUID restaurantId = extractRestaurantId(jwt);

        return new CustomAuthenticationToken(jwt, authorities, restaurantId);
    }

    private UUID extractRestaurantId(Jwt jwt) {
        Object restaurantId = jwt.getClaim("restaurant_id");
        if (restaurantId instanceof String extractedRestaurantId) {
            return UUID.fromString(extractedRestaurantId);
        }
        return null;
    }
}
