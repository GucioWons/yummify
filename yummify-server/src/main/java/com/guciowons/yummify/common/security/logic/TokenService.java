package com.guciowons.yummify.common.security.logic;

import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.config.security.CustomAuthenticationToken;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {
    public UserDTO getUser() {
        CustomAuthenticationToken auth = getCurrentAuthentication();
        UserDTO user = new UserDTO();
        user.setId(UUID.fromString(getClaim(auth, "sub")));
        user.setRestaurantId(auth.getRestaurantId());
        user.setEmail(getClaim(auth, "email"));
        user.setUsername(getClaim(auth, "preferred_username"));
        user.setFirstName(getClaim(auth, "given_name"));
        user.setLastName(getClaim(auth, "family_name"));
        return user;
    }

    public UUID getRestaurantId() {
        return getCurrentAuthentication().getRestaurantId();
    }

    private <T> T getClaim(JwtAuthenticationToken auth, String claim) {
        return auth.getToken().getClaim(claim);
    }

    private CustomAuthenticationToken getCurrentAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof CustomAuthenticationToken token) {
            return token;
        }
        throw new AccessDeniedException("Invalid authentication");
    }
}
