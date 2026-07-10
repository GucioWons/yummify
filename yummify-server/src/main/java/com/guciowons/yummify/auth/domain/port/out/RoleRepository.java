package com.guciowons.yummify.auth.domain.port.out;

import com.guciowons.yummify.auth.domain.model.Role;

public interface RoleRepository {
    void save(Role role);
}
