package com.guciowons.yummify.auth.infrastructure.in.rest;

import com.guciowons.yummify.auth.application.UserFacade;
import com.guciowons.yummify.auth.domain.model.User;
import com.guciowons.yummify.auth.infrastructure.in.rest.dto.UserManageDto;
import com.guciowons.yummify.auth.infrastructure.in.rest.dto.mapper.UserMapper;
import com.guciowons.yummify.common.security.application.SecuredByPermission;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.common.security.domain.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserFacade userFacade;
    private final UserMapper userMapper;

    @GetMapping
    @SecuredByPermission(Permission.USER_READ)
    public ResponseEntity<List<UserManageDto>> getUsers(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<UserManageDto> users = userFacade.getAllUsers(userPrincipal.restaurantId()).stream()
                .map(userMapper::toManageDto)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }

    @PostMapping
    @SecuredByPermission(Permission.USER_CREATE)
    public ResponseEntity<UserManageDto> createUser(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody UserManageDto dto
    ) {
        User user = userFacade.createUser(
                dto.email(),
                dto.username(),
                dto.firstName(),
                dto.lastName(),
                userPrincipal.restaurantId(),
                dto.roleId(),
                true
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userMapper.toManageDto(user));
    }
}
