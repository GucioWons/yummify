package com.guciowons.yummify.auth.application;

import com.guciowons.yummify.auth.application.model.mapper.UserCommandMapper;
import com.guciowons.yummify.auth.application.usecase.GetAllUsersUsecase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.guciowons.yummify.auth.application.fixture.AuthApplicationFixture.givenGetAllUsersQuery;
import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.givenUser;
import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.givenUserRestaurantId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserFacadeTest {
    private final GetAllUsersUsecase getAllUsersUsecase = mock(GetAllUsersUsecase.class);
    private final UserCommandMapper userCommandMapper = mock(UserCommandMapper.class);

    private final UserFacade underTest = new UserFacade(getAllUsersUsecase, userCommandMapper);

    @Test
    void shouldGetAllUsers() {
        // given
        var restaurantId = givenUserRestaurantId().value();
        var query = givenGetAllUsersQuery();
        var expectedResult = List.of(givenUser(false));

        when(userCommandMapper.toGetAllUsersQuery(restaurantId)).thenReturn(query);
        when(getAllUsersUsecase.getAllUsers(query)).thenReturn(expectedResult);

        // when
        var result = underTest.getAllUsers(restaurantId);

        // then
        verify(userCommandMapper).toGetAllUsersQuery(restaurantId);
        verify(getAllUsersUsecase).getAllUsers(query);

        assertThat(result).isEqualTo(expectedResult);
    }
}
