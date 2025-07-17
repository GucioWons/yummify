package com.guciowons.yummify.auth.client;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AdminTokenRequestDTO {
    private final String grant_type = "password";
    private final String client_id = "admin-cli";
    private String username;
    private String password;
}
