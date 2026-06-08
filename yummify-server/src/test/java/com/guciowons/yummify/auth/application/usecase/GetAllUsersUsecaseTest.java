package com.guciowons.yummify.auth.application.usecase;

import com.guciowons.yummify.auth.domain.port.out.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.guciowons.yummify.auth.application.fixture.AuthApplicationFixture.givenGetAllUsersQuery;
import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.givenUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetAllUsersUsecaseTest {
    private final UserRepository userRepository = mock(UserRepository.class);

    private final GetAllUsersUsecase underTest = new GetAllUsersUsecase(userRepository);

    @Test
    void shouldReturnAllUsers() {
        // given
        var query = givenGetAllUsersQuery();
        var expectedResult = List.of(givenUser(false));

        when(userRepository.getAllUsersByRestaurantId(query.restaurantId())).thenReturn(expectedResult);

        // when
        var result = underTest.getAllUsers(query);

        // then
        verify(userRepository).getAllUsersByRestaurantId(query.restaurantId());

        assertThat(result).isEqualTo(expectedResult);
    }
}
