package com.guciowons.yummify.auth.application;

import com.guciowons.yummify.auth.UserFacadePort;
import com.guciowons.yummify.auth.application.model.CreateUserCommand;
import com.guciowons.yummify.auth.application.model.GenerateOtpCommand;
import com.guciowons.yummify.auth.application.model.GetAllUsersQuery;
import com.guciowons.yummify.auth.application.model.mapper.UserCommandMapper;
import com.guciowons.yummify.auth.application.usecase.CreateUserUsecase;
import com.guciowons.yummify.auth.application.usecase.GenerateOtpUsecase;
import com.guciowons.yummify.auth.application.usecase.GetAllUsersUsecase;
import com.guciowons.yummify.auth.domain.model.User;
import com.guciowons.yummify.common.core.application.annotation.Facade;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class UserFacade implements UserFacadePort {
    private final GetAllUsersUsecase getAllUsersUsecase;
    private final CreateUserUsecase createUserUsecase;
    private final GenerateOtpUsecase generateOtpUsecase;
    private final UserCommandMapper userCommandMapper;

    @Override
    public List<User> getAllUsers(UUID restaurantId) {
        GetAllUsersQuery query = userCommandMapper.toGetAllUsersQuery(restaurantId);
        return getAllUsersUsecase.getAllUsers(query);
    }

    @Override
    public UUID createUserAndGetId(
            String email,
            String username,
            String firstName,
            String lastName,
            UUID restaurantId,
            UUID roleId,
            boolean withPassword
    ) {
        User user = createUser(email, username, firstName, lastName, restaurantId, roleId, withPassword);
        return user.getId().value();
    }

    @Override
    public User createUser(
            String email,
            String username,
            String firstName,
            String lastName,
            UUID restaurantId,
            UUID roleId,
            boolean withPassword
    ) {
        CreateUserCommand command = userCommandMapper.toCreateUserCommand(
                email, username, firstName, lastName, restaurantId, roleId, withPassword
        );

        return createUserUsecase.create(command);
    }

    @Override
    public String generateOtp(UUID userId) {
        GenerateOtpCommand command = userCommandMapper.toGenerateOtpCommand(userId);

        return generateOtpUsecase.generate(command).password().value();
    }
}
