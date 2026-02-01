package com.guciowons.yummify.auth.domain.model;

import com.guciowons.yummify.auth.domain.model.value.Email;
import com.guciowons.yummify.auth.domain.model.value.Password;
import com.guciowons.yummify.auth.domain.model.value.PersonalData;
import com.guciowons.yummify.auth.domain.model.value.Username;
import com.guciowons.yummify.restaurant.RestaurantId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private final Email email;
    private final Username username;
    private final PersonalData personalData;
    private final RestaurantId restaurantId;
    private final Password password;

    public static User of(
            Email email,
            Username username,
            PersonalData personalData,
            RestaurantId restaurantId,
            Password password
    ) {
        return new User(email, username, personalData, restaurantId, password);
    }

    public boolean hasPassword() {
        return password != null;
    }
}
