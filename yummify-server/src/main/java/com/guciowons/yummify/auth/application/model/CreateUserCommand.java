package com.guciowons.yummify.auth.application.model;

import com.guciowons.yummify.auth.domain.model.User;

public record CreateUserCommand(
        User.Email email,
        User.Username username,
        User.PersonalData personalData,
        User.RestaurantId restaurantId,
        boolean withPassword
) {
}
