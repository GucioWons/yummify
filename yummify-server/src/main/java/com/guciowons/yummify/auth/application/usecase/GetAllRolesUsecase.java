package com.guciowons.yummify.auth.application.usecase;

import com.guciowons.yummify.auth.application.model.GetAllRolesQuery;
import com.guciowons.yummify.auth.domain.model.Role;
import com.guciowons.yummify.auth.domain.port.out.RoleRepository;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Usecase
@RequiredArgsConstructor
public class GetAllRolesUsecase {
    private final RoleRepository roleRepository;

    public List<Role> getAllRoles(GetAllRolesQuery query) {
        return roleRepository.findAllByRestaurantId(query.restaurantId());
    }
}
