package com.guciowons.yummify.restaurant.logic;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "keycloak-admin", url = "http://localhost:8080/admin/realms/yummify")
public interface KeycloakAdminClient {
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Authorization: Bearer {token}")
    void createUser(@RequestHeader("Authorization") String authorization, @RequestBody Map<String, Object> user);

    @GetMapping(value = "/users/count", produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Authorization: Bearer {token}")
    int countUsersByEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String authorization);

    @PutMapping(value = "/users/{id}/reset-password", consumes = MediaType.APPLICATION_JSON_VALUE)
    void setPassword(@PathVariable("id") String userId,
                     @RequestHeader("Authorization") String authorization,
                     @RequestBody Map<String, Object> passwordPayload);
}
