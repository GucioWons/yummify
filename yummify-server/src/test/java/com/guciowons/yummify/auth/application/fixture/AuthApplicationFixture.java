package com.guciowons.yummify.auth.application.fixture;

import com.guciowons.yummify.auth.application.model.*;
import com.guciowons.yummify.common.security.domain.Permission;

import java.util.Collections;

import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.*;

public class AuthApplicationFixture {
    public static CreateUserCommand givenCreateUserCommand(boolean withPassword) {
        return new CreateUserCommand(
                givenUserEmail(),
                givenUserUsername(),
                givenUserPersonalData(),
                givenUserRestaurantId(),
                givenRoleId(1),
                withPassword
        );
    }

    public static GenerateOtpCommand givenGenerateOtpCommand() {
        return new GenerateOtpCommand(givenUserExternalId(1));
    }

    public static GetAllUsersQuery givenGetAllUsersQuery() {
        return new GetAllUsersQuery(givenUserRestaurantId());
    }

    public static CreateRoleCommand givenCreateRoleCommand() {
        return new CreateRoleCommand(givenRoleRestaurantId(1), givenRoleName(1), Collections.singleton(Permission.OWNER));
    }

    public static GetAllRolesQuery givenGetAllRolesQuery() {
        return new GetAllRolesQuery(givenRoleRestaurantId(1));
    }
}
