package com.guciowons.yummify.auth.application;

import com.guciowons.yummify.auth.application.model.GetAllUsersQuery;
import com.guciowons.yummify.auth.application.model.mapper.UserCommandMapper;
import com.guciowons.yummify.auth.application.usecase.GetAllUsersUsecase;
import com.guciowons.yummify.auth.domain.model.User;
import com.guciowons.yummify.common.core.application.annotation.Facade;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class UserFacade {
    private final GetAllUsersUsecase getAllUsersUsecase;
    private final UserCommandMapper userCommandMapper;

    public List<User> getAllUsers(UUID restaurantId) {
        GetAllUsersQuery query = userCommandMapper.toGetAllUsersQuery(restaurantId);
        return getAllUsersUsecase.getAllUsers(query);
    }
}
