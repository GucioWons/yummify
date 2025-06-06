package com.guciowons.yummify.restaurant.logic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminTokenRequestDTO {
    private String grant_type; private String client_id; private String username; private String password;
}
