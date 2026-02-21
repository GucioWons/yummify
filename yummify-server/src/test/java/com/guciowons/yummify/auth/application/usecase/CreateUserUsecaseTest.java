package com.guciowons.yummify.auth.application.usecase;

import com.guciowons.yummify.auth.domain.exception.AccountExistsByEmailException;
import com.guciowons.yummify.auth.domain.exception.AccountExistsByUsernameException;
import com.guciowons.yummify.auth.domain.model.User;
import com.guciowons.yummify.auth.domain.port.out.PasswordGeneratorPort;
import com.guciowons.yummify.auth.domain.port.out.UserRepository;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static com.guciowons.yummify.auth.application.fixture.AuthApplicationFixture.givenCreateUserCommand;
import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateUserUsecaseTest {
    private final PasswordGeneratorPort passwordGenerator = mock(PasswordGeneratorPort.class);
    private final UserRepository userRepository = mock(UserRepository.class);

    private final CreateUserUsecase underTest = new CreateUserUsecase(passwordGenerator, userRepository);

    @Test
    void shouldCreateUserWithPassword_WhenCommandWithPasswordIsTrue() throws DomainException {
        // given
        var command = givenCreateUserCommand(true);
        var user = givenUser(true);
        var userId = givenUserExternalId(1);
        user.assignId(userId);
        var password = givenPassword();

        when(passwordGenerator.generate(12, 2, 2, 2)).thenReturn(password);
        when(userRepository.createUser(any())).thenReturn(user);

        // when
        var result = underTest.create(command);

        // then
        verify(userRepository).existsByEmail(command.email());
        verify(userRepository).existsByUsername(command.username());
        verify(passwordGenerator).generate(12, 2, 2, 2);


        var userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).createUser(userCaptor.capture());
        var capturedUser = userCaptor.getValue();
        assertThat(capturedUser.getPassword()).isEqualTo(password);

        assertThat(result).isEqualTo(userId);
    }

    @Test
    void shouldCreateUserWithoutPassword_WhenCommandWithPasswordIsFalse() throws DomainException {
        // given
        var command = givenCreateUserCommand(false);
        var user = givenUser(true);
        var userId = givenUserExternalId(1);
        user.assignId(userId);

        when(userRepository.createUser(any())).thenReturn(user);

        // when
        var result = underTest.create(command);

        // then
        verify(userRepository).existsByEmail(command.email());
        verify(userRepository).existsByUsername(command.username());
        verify(passwordGenerator, never()).generate(anyInt(), anyInt(), anyInt(), anyInt());

        var userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).createUser(userCaptor.capture());
        var capturedUser = userCaptor.getValue();
        assertThat(capturedUser.getPassword()).isNull();

        assertThat(result).isEqualTo(userId);
    }

    @Test
    void shouldThrowException_whenUserExistsByEmail() {
        // given
        var command = givenCreateUserCommand(false);

        when(userRepository.existsByEmail(command.email())).thenReturn(true);

        // when
        assertThatThrownBy(() -> underTest.create(command)).isInstanceOf(AccountExistsByEmailException.class);

        // then
        verify(userRepository).existsByEmail(command.email());
        verify(userRepository, never()).existsByUsername(any());
        verify(passwordGenerator, never()).generate(anyInt(), anyInt(), anyInt(), anyInt());
        verify(userRepository, never()).createUser(any());
    }

    @Test
    void shouldThrowException_whenUserExistsByUsername() {
        // given
        var command = givenCreateUserCommand(false);

        when(userRepository.existsByUsername(command.username())).thenReturn(true);

        // when
        assertThatThrownBy(() -> underTest.create(command)).isInstanceOf(AccountExistsByUsernameException.class);

        // then
        verify(userRepository).existsByEmail(command.email());
        verify(userRepository).existsByUsername(any());
        verify(passwordGenerator, never()).generate(anyInt(), anyInt(), anyInt(), anyInt());
        verify(userRepository, never()).createUser(any());
    }
}