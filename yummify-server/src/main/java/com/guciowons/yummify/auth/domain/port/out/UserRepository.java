package com.guciowons.yummify.auth.domain.port.out;

import com.guciowons.yummify.auth.domain.model.Otp;
import com.guciowons.yummify.auth.domain.model.User;
import com.guciowons.yummify.auth.domain.model.value.Email;
import com.guciowons.yummify.auth.domain.model.value.UserId;
import com.guciowons.yummify.auth.domain.model.value.Username;

public interface UserRepository {
    boolean existsByEmail(Email email);

    boolean existsByUsername(Username username);

    UserId createUser(User user);

    void updateOtp(UserId userId, Otp otp);
}
