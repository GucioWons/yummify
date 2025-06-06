package com.guciowons.yummify.auth;

import java.util.UUID;

public interface PublicAuthService {
    UUID createUserAndGetId(UserRequestDTO userRequest);
}
