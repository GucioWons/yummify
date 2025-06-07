package com.guciowons.yummify.config.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.UUID;

@Getter
public class CustomAuthenticationToken extends JwtAuthenticationToken {
    private final UUID restaurantId;

    public CustomAuthenticationToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities, UUID restaurantId) {
        super(jwt, authorities);
        this.restaurantId = restaurantId;
    }
}
