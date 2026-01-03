package com.guciowons.yummify.auth.application;

import com.guciowons.yummify.auth.application.usecase.CreateUserUsecase;
import com.guciowons.yummify.auth.application.usecase.GenerateOtpUsecase;
import com.guciowons.yummify.auth.exposed.AuthFacadePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthFacade implements AuthFacadePort {
    private final CreateUserUsecase createUserUsecase;
    private final GenerateOtpUsecase generateOtpUsecase;

    @Override
    public UUID createUser(String email, String username, String firstName, String lastName, UUID restaurantId) {
        return createUserUsecase.create(email, username, firstName, lastName, restaurantId);
    }

    @Override
    public UUID createUserWithPassword(String email, String username, String firstName, String lastName, UUID restaurantId) {
        return createUserUsecase.createWithPassword(email, username, firstName, lastName, restaurantId);
    }

    @Override
    public String generateOtp(UUID userId) {
        return generateOtpUsecase.generate(userId);
    }
}
