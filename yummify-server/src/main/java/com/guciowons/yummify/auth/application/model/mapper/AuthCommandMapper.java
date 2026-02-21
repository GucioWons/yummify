package com.guciowons.yummify.auth.application.model.mapper;

import com.guciowons.yummify.auth.application.model.CreateUserCommand;
import com.guciowons.yummify.auth.application.model.GenerateOtpCommand;
import com.guciowons.yummify.auth.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AuthCommandMapper {

    @Mapping(target = "personalData", expression = "java(toPersonalData(firstName, lastName))")
    CreateUserCommand toCreateUserCommand(
            String email,
            String username,
            String firstName,
            String lastName,
            UUID restaurantId,
            boolean withPassword
    );

    GenerateOtpCommand toGenerateOtpCommand(UUID userId);

    default User.RestaurantId toRestaurantId(UUID restaurantId) {
        return User.RestaurantId.of(restaurantId);
    }

    default User.Email toEmail(String email) {
        return User.Email.of(email);
    }

    default User.PersonalData toPersonalData(String firstName, String lastName) {
        return User.PersonalData.of(firstName, lastName);
    }

    default User.Username toUsername(String username) {
        return User.Username.of(username);
    }

    default User.ExternalId toExternalId(String userId) {
        return User.ExternalId.of(userId);
    }
}
