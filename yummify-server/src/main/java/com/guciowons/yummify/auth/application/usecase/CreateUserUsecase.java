package com.guciowons.yummify.auth.application.usecase;

import com.guciowons.yummify.auth.domain.model.UserRequest;
import com.guciowons.yummify.auth.domain.service.UserCreator;
import com.guciowons.yummify.auth.domain.service.UserPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateUserUsecase {
    private final UserCreator userCreator;
    private final UserPasswordService userPasswordService;

    public UUID create(String email, String username, String firstName, String lastName, UUID restaurantId) {
        return userCreator.create(new UserRequest(email, username, firstName, lastName, restaurantId));
    }

    public UUID createWithPassword(String email, String username, String firstName, String lastName, UUID restaurantId) {
        UUID userId = userCreator.create(new UserRequest(email, username, firstName, lastName, restaurantId));

        userPasswordService.set(userId);

        return userId;
    }
}
