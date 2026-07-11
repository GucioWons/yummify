package com.guciowons.yummify.auth.application.service;

import com.guciowons.yummify.auth.domain.exception.RoleNotFoundException;
import com.guciowons.yummify.auth.domain.model.Role;
import com.guciowons.yummify.auth.domain.port.out.RoleRepository;
import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class RoleLookupService {
    private final RoleRepository roleRepository;

    public Role getByIdAndRestaurantId(Role.Id id, Role.RestaurantId restaurantId) {
        return roleRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> new RoleNotFoundException(id));
    }
}
