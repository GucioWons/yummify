package com.guciowons.yummify.auth.client;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "keycloak-auth", url = "${spring.security.oauth2.resourceserver.jwt.admin-auth-uri}")
public interface KeycloakAuthClient {
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    AdminTokenResponseDTO getAdminAccessToken(@RequestBody AdminTokenRequestDTO form);
}
