package com.guciowons.yummify.auth.application;

import com.guciowons.yummify.auth.application.model.mapper.AuthCommandMapper;
import com.guciowons.yummify.auth.application.usecase.CreateUserUsecase;
import com.guciowons.yummify.auth.application.usecase.GenerateOtpUsecase;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.auth.application.fixture.AuthApplicationFixture.givenCreateUserCommand;
import static com.guciowons.yummify.auth.application.fixture.AuthApplicationFixture.givenGenerateOtpCommand;
import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AuthFacadeTest {
    private final CreateUserUsecase createUserUsecase = mock(CreateUserUsecase.class);
    private final GenerateOtpUsecase generateOtpUsecase = mock(GenerateOtpUsecase.class);
    private final AuthCommandMapper authCommandMapper = mock(AuthCommandMapper.class);

    private final AuthFacade underTest = new AuthFacade(
            createUserUsecase,
            generateOtpUsecase,
            authCommandMapper
    );

    @Test
    void shouldCreateUser() throws DomainException {
        // given
        var email = givenUserEmail().value();
        var username = givenUserUsername().value();
        var firstName = givenUserPersonalData().firstName();
        var lastName = givenUserPersonalData().lastName();
        var restaurantId = givenUserRestaurantId().value();
        var withPassword = true;
        var command = givenCreateUserCommand(true);
        var expectedUserId = givenUserExternalId(1);

        when(authCommandMapper.toCreateUserCommand(email, username, firstName, lastName, restaurantId, withPassword))
                .thenReturn(command);
        when(createUserUsecase.create(command)).thenReturn(expectedUserId);

        // when
        var result = underTest.createUser(email, username, firstName, lastName, restaurantId, withPassword);

        // then
        verify(authCommandMapper).toCreateUserCommand(email, username, firstName, lastName, restaurantId, withPassword);
        verify(createUserUsecase).create(command);

        assertThat(result).isEqualTo(expectedUserId.value());
    }

    @Test
    void shouldGenerateOtp() {
        // given
        var userId = givenUserExternalId(1).value();
        var command = givenGenerateOtpCommand();
        var expectedOtp = givenOtp();

        when(authCommandMapper.toGenerateOtpCommand(userId)).thenReturn(command);
        when(generateOtpUsecase.generate(command)).thenReturn(expectedOtp);

        // when
        var result = underTest.generateOtp(userId);

        // then
        assertThat(result).isEqualTo(expectedOtp.password().value());
    }
}