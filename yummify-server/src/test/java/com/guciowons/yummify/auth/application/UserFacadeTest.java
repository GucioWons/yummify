package com.guciowons.yummify.auth.application;

import com.guciowons.yummify.auth.application.model.mapper.UserCommandMapper;
import com.guciowons.yummify.auth.application.usecase.CreateUserUsecase;
import com.guciowons.yummify.auth.application.usecase.GenerateOtpUsecase;
import com.guciowons.yummify.auth.application.usecase.GetAllUsersUsecase;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.guciowons.yummify.auth.application.fixture.AuthApplicationFixture.*;
import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserFacadeTest {
    private final GetAllUsersUsecase getAllUsersUsecase = mock(GetAllUsersUsecase.class);
    private final CreateUserUsecase createUserUsecase = mock(CreateUserUsecase.class);
    private final GenerateOtpUsecase generateOtpUsecase = mock(GenerateOtpUsecase.class);
    private final UserCommandMapper userCommandMapper = mock(UserCommandMapper.class);

    private final UserFacade underTest = new UserFacade(getAllUsersUsecase, createUserUsecase, generateOtpUsecase, userCommandMapper);

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

    @Test
    void shouldCreateUserAndGetId() throws DomainException {
        // given
        var email = givenUserEmail().value();
        var username = givenUserUsername().value();
        var firstName = givenUserPersonalData().firstName();
        var lastName = givenUserPersonalData().lastName();
        var restaurantId = givenUserRestaurantId().value();
        var roleId = givenRoleId(1).value();
        var withPassword = true;
        var command = givenCreateUserCommand(true);
        var expectedUser = givenUser(true);
        expectedUser.assignId(givenUserExternalId(1));

        when(userCommandMapper.toCreateUserCommand(email, username, firstName, lastName, restaurantId, roleId, withPassword))
                .thenReturn(command);
        when(createUserUsecase.create(command)).thenReturn(expectedUser);

        // when
        var result = underTest.createUserAndGetId(email, username, firstName, lastName, restaurantId, roleId, withPassword);

        // then
        verify(userCommandMapper).toCreateUserCommand(email, username, firstName, lastName, restaurantId, roleId, withPassword);
        verify(createUserUsecase).create(command);

        assertThat(result).isEqualTo(expectedUser.getId().value());
    }

    @Test
    void shouldCreateUser() throws DomainException {
        // given
        var email = givenUserEmail().value();
        var username = givenUserUsername().value();
        var firstName = givenUserPersonalData().firstName();
        var lastName = givenUserPersonalData().lastName();
        var restaurantId = givenUserRestaurantId().value();
        var roleId = givenRoleId(1).value();
        var withPassword = true;
        var command = givenCreateUserCommand(true);
        var expectedUser = givenUser(true);
        expectedUser.assignId(givenUserExternalId(1));

        when(userCommandMapper.toCreateUserCommand(email, username, firstName, lastName, restaurantId, roleId, withPassword))
                .thenReturn(command);
        when(createUserUsecase.create(command)).thenReturn(expectedUser);

        // when
        var result = underTest.createUser(email, username, firstName, lastName, restaurantId, roleId, withPassword);

        // then
        verify(userCommandMapper).toCreateUserCommand(email, username, firstName, lastName, restaurantId, roleId, withPassword);
        verify(createUserUsecase).create(command);

        assertThat(result).isEqualTo(expectedUser);
    }

    @Test
    void shouldGenerateOtp() {
        // given
        var userId = givenUserExternalId(1).value();
        var command = givenGenerateOtpCommand();
        var expectedOtp = givenOtp();

        when(userCommandMapper.toGenerateOtpCommand(userId)).thenReturn(command);
        when(generateOtpUsecase.generate(command)).thenReturn(expectedOtp);

        // when
        var result = underTest.generateOtp(userId);

        // then
        assertThat(result).isEqualTo(expectedOtp.password().value());
    }
}
