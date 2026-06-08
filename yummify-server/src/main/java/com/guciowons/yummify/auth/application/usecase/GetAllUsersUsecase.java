package com.guciowons.yummify.auth.application.usecase;

import com.guciowons.yummify.auth.application.model.GetAllUsersQuery;
import com.guciowons.yummify.auth.domain.model.User;
import com.guciowons.yummify.auth.domain.port.out.UserRepository;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Usecase
@RequiredArgsConstructor
public class GetAllUsersUsecase {
    private final UserRepository userRepository;

    public List<User> getAllUsers(GetAllUsersQuery query) {
        return userRepository.getAllUsersByRestaurantId(query.restaurantId());
    }
}
