package com.guciowons.yummify.restaurant.logic;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "keycloak-auth", url = "http://localhost:8080/realms/master/protocol/openid-connect/token")
public interface KeycloakAuthClient {
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    AdminTokenResponseDTO getAdminAccessToken(@RequestBody AdminTokenRequestDTO form);
}
