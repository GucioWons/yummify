package com.guciowons.yummify.auth.client;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AdminTokenRequestDTO {
    private String grant_type = "password";
    private String client_id = "admin-cli";
    private String username;
    private String password;

    public AdminTokenRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
