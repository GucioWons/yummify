package com.guciowons.yummify.auth.client;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminTokenRequestDTO {
    private String grant_type; private String client_id; private String username; private String password;
}
