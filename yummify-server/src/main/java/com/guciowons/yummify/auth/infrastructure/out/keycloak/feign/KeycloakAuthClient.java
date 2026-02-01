package com.guciowons.yummify.auth.infrastructure.out.keycloak.feign;

import com.guciowons.yummify.auth.infrastructure.out.keycloak.model.AdminTokenRequest;
import com.guciowons.yummify.auth.infrastructure.out.keycloak.model.AdminTokenResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "keycloak-auth", url = "${modules.auth.keycloak.auth-uri}")
public interface KeycloakAuthClient {
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    AdminTokenResponse getAdminAccessToken(@RequestBody AdminTokenRequest form);
}
