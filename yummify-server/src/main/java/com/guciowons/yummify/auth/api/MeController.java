package com.guciowons.yummify.auth.api;

import com.guciowons.yummify.auth.UserDTO;
import com.guciowons.yummify.common.request.RequestContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("me")
@RequiredArgsConstructor
public class MeController {
    @GetMapping
    public ResponseEntity<UserDTO> me() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(RequestContext.get().getUser());
    }
}
