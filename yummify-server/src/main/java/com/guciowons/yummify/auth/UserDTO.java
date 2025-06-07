package com.guciowons.yummify.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private UUID restaurantId;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
}
