package com.guciowons.yummify.auth.infrastructure.in.rest;

import com.guciowons.yummify.auth.application.UserFacade;
import com.guciowons.yummify.auth.infrastructure.in.rest.dto.UserManageDto;
import com.guciowons.yummify.auth.infrastructure.in.rest.dto.mapper.UserMapper;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserFacade userFacade;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserManageDto>> getUsers(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<UserManageDto> users = userFacade.getAllUsers(userPrincipal.restaurantId()).stream()
                .map(userMapper::toManageDto)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }
}
