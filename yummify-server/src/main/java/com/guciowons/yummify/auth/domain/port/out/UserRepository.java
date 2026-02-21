package com.guciowons.yummify.auth.domain.port.out;

import com.guciowons.yummify.auth.domain.model.Otp;
import com.guciowons.yummify.auth.domain.model.User;

public interface UserRepository {
    boolean existsByEmail(User.Email email);

    boolean existsByUsername(User.Username username);

    User createUser(User user);

    void updateOtp(User.ExternalId userId, Otp otp);
}
