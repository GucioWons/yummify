package com.guciowons.yummify.auth.application.usecase;

import com.guciowons.yummify.auth.domain.model.Otp;
import com.guciowons.yummify.auth.domain.port.out.PasswordGeneratorPort;
import com.guciowons.yummify.auth.domain.port.out.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static com.guciowons.yummify.auth.application.fixture.AuthApplicationFixture.givenGenerateOtpCommand;
import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.givenPassword;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GenerateOtpUsecaseTest {
    private final PasswordGeneratorPort passwordGenerator = mock(PasswordGeneratorPort.class);
    private final UserRepository userRepository = mock(UserRepository.class);

    private final GenerateOtpUsecase underTest = new GenerateOtpUsecase(passwordGenerator, userRepository);

    @Test
    void shouldCreateOtpForUser() {
        // given
        var command = givenGenerateOtpCommand();
        var otp = givenPassword();

        when(passwordGenerator.generate(16, 3, 3, 3)).thenReturn(otp);

        // when
        var result = underTest.generate(command);

        // then
        verify(passwordGenerator).generate(16, 3, 3, 3);

        var otpCaptor = ArgumentCaptor.forClass(Otp.class);
        verify(userRepository).updateOtp(eq(command.userId()), otpCaptor.capture());
        var capturedOtp = otpCaptor.getValue();

        assertThat(result).isEqualTo(capturedOtp);
    }
}