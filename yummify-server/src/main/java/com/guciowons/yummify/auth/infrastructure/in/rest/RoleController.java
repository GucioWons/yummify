package com.guciowons.yummify.auth.infrastructure.in.rest;

import com.guciowons.yummify.auth.application.RoleFacade;
import com.guciowons.yummify.auth.domain.model.Role;
import com.guciowons.yummify.auth.infrastructure.in.rest.dto.RoleManageDto;
import com.guciowons.yummify.auth.infrastructure.in.rest.dto.mapper.RoleMapper;
import com.guciowons.yummify.common.security.application.SecuredByPermission;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.common.security.domain.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleFacade roleFacade;
    private final RoleMapper roleMapper;

    @PostMapping
    @SecuredByPermission(Permission.ROLE_CREATE)
    public ResponseEntity<RoleManageDto> create(
            @RequestBody RoleManageDto dto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        Role role = roleFacade.create(userPrincipal.restaurantId(), dto.name().translations(), dto.permissions());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(roleMapper.toManageDto(role));
    }
}
