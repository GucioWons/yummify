package com.guciowons.yummify.auth.infrastructure.in.rest;

import com.guciowons.yummify.auth.infrastructure.in.rest.dto.UserDTO;
import com.guciowons.yummify.auth.infrastructure.in.rest.dto.mapper.UserMapper;
import com.guciowons.yummify.common.security.application.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/me")
@RequiredArgsConstructor
public class MeController {
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<UserDTO> me(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userMapper.mapToDTO(userPrincipal));
    }
}
