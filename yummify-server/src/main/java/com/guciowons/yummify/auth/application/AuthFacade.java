package com.guciowons.yummify.auth.application;

import com.guciowons.yummify.auth.AuthFacadePort;
import com.guciowons.yummify.auth.application.model.CreateUserCommand;
import com.guciowons.yummify.auth.application.model.GenerateOtpCommand;
import com.guciowons.yummify.auth.application.model.mapper.AuthCommandMapper;
import com.guciowons.yummify.auth.application.usecase.CreateUserUsecase;
import com.guciowons.yummify.auth.application.usecase.GenerateOtpUsecase;
import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.exception.infrastructure.DomainExceptionHandler;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class AuthFacade implements AuthFacadePort {
    private final CreateUserUsecase createUserUsecase;
    private final GenerateOtpUsecase generateOtpUsecase;
    private final DomainExceptionHandler authDomainExceptionHandler;
    private final AuthCommandMapper authCommandMapper;

    @Override
    public UUID createUser(
            String email,
            String username,
            String firstName,
            String lastName,
            UUID restaurantId,
            boolean withPassword
    ) {
        CreateUserCommand command = authCommandMapper.toCreateUserCommand(
                email, username, firstName, lastName, restaurantId, withPassword
        );

        return authDomainExceptionHandler.handle(() -> createUserUsecase.create(command).value());
    }

    @Override
    public String generateOtp(UUID userId) {
        GenerateOtpCommand command = authCommandMapper.toGenerateOtpCommand(userId);

        return generateOtpUsecase.generate(command).password().value();
    }
}
