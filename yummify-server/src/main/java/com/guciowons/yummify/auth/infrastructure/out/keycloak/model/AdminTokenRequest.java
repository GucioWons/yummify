package com.guciowons.yummify.auth.infrastructure.out.keycloak.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AdminTokenRequest {
    private String grant_type = "password";
    private String client_id = "admin-cli";
    private String username;
    private String password;

    public AdminTokenRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
