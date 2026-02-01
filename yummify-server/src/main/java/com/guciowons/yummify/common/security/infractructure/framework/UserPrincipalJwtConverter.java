package com.guciowons.yummify.common.security.infractructure.framework;

import com.guciowons.yummify.common.security.application.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserPrincipalJwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final RoleConverter roleConverter;

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {

        UserPrincipal principal = new UserPrincipal(
                UUID.fromString(jwt.getSubject()),
                UUID.fromString(jwt.getClaimAsString("restaurant_id")),
                jwt.getClaimAsString("email"),
                jwt.getClaimAsString("preferred_username"),
                jwt.getClaimAsString("given_name"),
                jwt.getClaimAsString("family_name")
        );

        Collection<GrantedAuthority> authorities = roleConverter.convert(jwt);

        return new UsernamePasswordAuthenticationToken(
                principal,
                jwt,
                authorities
        );
    }
}
