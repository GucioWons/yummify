package com.guciowons.yummify.auth.client;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "keycloak-admin", url = "${spring.security.oauth2.yummify.uri}")
public interface KeycloakAdminClient {
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    void createUser(
            @RequestHeader("Authorization") String authorization,
            @RequestBody UserRepresentation user
    );

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserRepresentation> getUserByEmail(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("email") String email
    );

    @GetMapping(value = "/users/count", produces = MediaType.APPLICATION_JSON_VALUE)
    int countUsersByEmail(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("email") String email
    );

    @GetMapping(value = "/users/count", produces = MediaType.APPLICATION_JSON_VALUE)
    int countUsersByUsername(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("username") String username
    );

    @PutMapping(value = "/users/{id}/reset-password", consumes = MediaType.APPLICATION_JSON_VALUE)
    void setPassword(
            @PathVariable("id") String userId,
            @RequestHeader("Authorization") String authorization,
            @RequestBody PasswordRequestDTO passwordPayload
    );

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserRepresentation getUser(
            @PathVariable("id") String userId,
            @RequestHeader("Authorization") String authorization
    );

    @PutMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void updateUser(
            @PathVariable("id") String userId,
            @RequestHeader("Authorization") String authorization,
            @RequestBody UserRepresentation userResponseDTO
    );
}
