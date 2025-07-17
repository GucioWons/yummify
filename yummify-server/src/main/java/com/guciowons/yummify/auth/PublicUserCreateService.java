package com.guciowons.yummify.auth;

import java.util.UUID;

public interface PublicUserCreateService {
    UUID createUser(UserRequestDTO userRequest);

    UUID createUserWithPassword(UserRequestDTO userRequest);
}
