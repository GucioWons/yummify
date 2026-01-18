package com.guciowons.yummify.auth.application.model;

import com.guciowons.yummify.auth.domain.model.value.Email;
import com.guciowons.yummify.auth.domain.model.value.PersonalData;
import com.guciowons.yummify.restaurant.RestaurantId;
import com.guciowons.yummify.auth.domain.model.value.Username;

public record CreateUserCommand(
        Email email,
        Username username,
        PersonalData personalData,
        RestaurantId restaurantId,
        boolean withPassword
) {
}
