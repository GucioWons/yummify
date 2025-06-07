package com.guciowons.yummify.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private Map<String, String> attributes;
    private final boolean enabled = true;
}
