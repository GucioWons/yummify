package com.guciowons.yummify.auth.logic;

import com.guciowons.yummify.auth.UserDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {
    public UserDTO getUser() {
        JwtAuthenticationToken auth = getCurrentAuthentication();
        UserDTO user = new UserDTO();
        user.setId(UUID.fromString(getClaim(auth, "sub")));
        user.setRestaurantId(UUID.fromString(getClaim(auth, "restaurant_id")));
        user.setEmail(getClaim(auth, "email"));
        user.setUsername(getClaim(auth, "preferred_username"));
        user.setFirstName(getClaim(auth, "given_name"));
        user.setLastName(getClaim(auth, "family_name"));
        return user;
    }

    private <T> T getClaim(JwtAuthenticationToken auth, String claim) {
        return auth.getToken().getClaim(claim);
    }

    private JwtAuthenticationToken getCurrentAuthentication() {
        return (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    }
}
