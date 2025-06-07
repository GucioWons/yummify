package com.guciowons.yummify.auth.logic;

import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.common.security.logic.TokenService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {
    private final TokenService tokenService = new TokenService();

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void shouldReturnUserFromJwtToken() {
        UUID userId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        String email = "user@example.com";
        String username = "user123";
        String firstName = "Jane";
        String lastName = "Doe";

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userId.toString());
        claims.put("restaurant_id", restaurantId.toString());
        claims.put("email", email);
        claims.put("preferred_username", username);
        claims.put("given_name", firstName);
        claims.put("family_name", lastName);

        Jwt jwt = new Jwt("tokenValue", Instant.now(), Instant.now().plusSeconds(3600), Map.of("alg", "none"), claims);
        JwtAuthenticationToken authToken = new JwtAuthenticationToken(jwt);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);

        UserDTO result = tokenService.getUser();

        assertEquals(userId, result.getId());
        assertEquals(restaurantId, result.getRestaurantId());
        assertEquals(email, result.getEmail());
        assertEquals(username, result.getUsername());
        assertEquals(firstName, result.getFirstName());
        assertEquals(lastName, result.getLastName());
    }
}
