package com.guciowons.yummify.auth.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordRequestDTO {
    private final String type = "password";
    private String value;
    private final boolean temporary = false;
}
