package com.guciowons.yummify.auth.application.model.mapper;

import com.guciowons.yummify.auth.application.model.GetAllUsersQuery;
import com.guciowons.yummify.auth.domain.model.User;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserCommandMapper {
    GetAllUsersQuery toGetAllUsersQuery(UUID restaurantId);

    default User.RestaurantId toRestaurantId(UUID restaurantId) {
        return User.RestaurantId.of(restaurantId);
    }
}
