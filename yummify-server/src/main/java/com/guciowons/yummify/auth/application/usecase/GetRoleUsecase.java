package com.guciowons.yummify.auth.application.usecase;

import com.guciowons.yummify.auth.application.model.GetRoleQuery;
import com.guciowons.yummify.auth.application.service.RoleLookupService;
import com.guciowons.yummify.auth.domain.model.Role;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class GetRoleUsecase {
    private final RoleLookupService roleLookupService;

    public Role getRole(GetRoleQuery query) {
        return roleLookupService.getByIdAndRestaurantId(query.id(), query.restaurantId());
    }
}
