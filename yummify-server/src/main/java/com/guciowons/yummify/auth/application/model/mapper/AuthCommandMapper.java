package com.guciowons.yummify.auth.application.model.mapper;

import com.guciowons.yummify.auth.application.model.CreateUserCommand;
import com.guciowons.yummify.auth.application.model.GenerateOtpCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AuthCommandMapper {

    @Mapping(target = "email", expression = "java(Email.of(email))")
    @Mapping(target = "username", expression = "java(Username.of(username))")
    @Mapping(target = "personalData", expression = "java(PersonalData.of(firstName, lastName))")
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    CreateUserCommand toCreateUserCommand(
            String email,
            String username,
            String firstName,
            String lastName,
            UUID restaurantId,
            boolean withPassword
    );

    @Mapping(target = "userId", expression = "java(UserId.of(userId))")
    GenerateOtpCommand toGenerateOtpCommand(UUID userId);
}
