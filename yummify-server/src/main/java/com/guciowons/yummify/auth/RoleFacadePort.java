package com.guciowons.yummify.auth;

import com.guciowons.yummify.auth.domain.model.Role;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface RoleFacadePort {
    UUID createAndGetId(UUID restaurantId, Map<String, String> name, Set<String> parameters);

    Role create(UUID restaurantId, Map<String, String> name, Set<String> permissions);

    List<Role> getAll(UUID restaurantId);
}
