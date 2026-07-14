package com.guciowons.yummify.auth.application.usecase;

import com.guciowons.yummify.auth.application.model.CreateUserCommand;
import com.guciowons.yummify.auth.application.service.RoleLookupService;
import com.guciowons.yummify.auth.domain.exception.AccountExistsByEmailException;
import com.guciowons.yummify.auth.domain.exception.AccountExistsByUsernameException;
import com.guciowons.yummify.auth.domain.model.Password;
import com.guciowons.yummify.auth.domain.model.Role;
import com.guciowons.yummify.auth.domain.model.User;
import com.guciowons.yummify.auth.domain.port.out.PasswordGeneratorPort;
import com.guciowons.yummify.auth.domain.port.out.UserRepository;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class CreateUserUsecase {
    private final PasswordGeneratorPort passwordGenerator;
    private final UserRepository userRepository;
    private final RoleLookupService roleLookupService;

    public User create(CreateUserCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new AccountExistsByEmailException();
        }

        if (userRepository.existsByUsername(command.username())) {
            throw new AccountExistsByUsernameException();
        }

        Role role = roleLookupService.getByIdAndRestaurantId(
                command.roleId(),
                Role.RestaurantId.of(command.restaurantId().value())
        );

        User user = User.create(
                command.restaurantId(),
                command.email(),
                command.username(),
                command.personalData(),
                generatePassword(command.withPassword()),
                role
        );

        return userRepository.createUser(user);
    }

    private Password generatePassword(boolean withPassword) {
        return withPassword
                ? passwordGenerator.generate(12, 2, 2, 2)
                : null;
    }
}
