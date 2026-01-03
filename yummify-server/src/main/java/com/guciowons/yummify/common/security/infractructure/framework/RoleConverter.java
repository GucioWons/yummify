package com.guciowons.yummify.common.security.infractructure.framework;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RoleConverter {
    public Collection<GrantedAuthority> convert(Jwt jwt) {

        return Optional.ofNullable(jwt.getClaimAsMap("realm_access"))
                .map(realm -> safeGetRolesFromMap(realm.get("roles")))
                .orElse(Collections.emptyList())
                .stream()
                .filter(role -> !role.isEmpty() && !role.equals("default-roles-yummify"))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    private Collection<String> safeGetRolesFromMap(Object object) {
        if (!(object instanceof Collection<?>)) {
            return Collections.emptyList();
        }

        return ((Collection<?>) object).stream()
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .collect(Collectors.toList());
    }
}
