package com.guciowons.yummify.auth.application.usecase;

import com.guciowons.yummify.auth.application.model.GenerateOtpCommand;
import com.guciowons.yummify.auth.domain.model.Otp;
import com.guciowons.yummify.auth.domain.port.out.PasswordGeneratorPort;
import com.guciowons.yummify.auth.domain.port.out.UserRepository;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Usecase
@RequiredArgsConstructor
public class GenerateOtpUsecase {
    private final PasswordGeneratorPort passwordGenerator;
    private final UserRepository userRepository;

    public Otp generate(GenerateOtpCommand generateOtpCommand) {
        Otp otp = Otp.of(
                passwordGenerator.generate(16, 3, 3, 3),
                LocalDateTime.now().plus(Duration.ofMinutes(5))
        );

        userRepository.updateOtp(generateOtpCommand.userId(), otp);

        return otp;
    }
}
