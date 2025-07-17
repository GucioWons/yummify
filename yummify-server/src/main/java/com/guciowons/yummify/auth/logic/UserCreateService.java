package com.guciowons.yummify.auth.logic;

import com.guciowons.yummify.auth.PublicUserCreateService;
import com.guciowons.yummify.auth.UserRequestDTO;
import com.guciowons.yummify.auth.client.KeycloakAdminClient;
import com.guciowons.yummify.auth.client.KeycloakAuthClient;
import com.guciowons.yummify.auth.client.PasswordRequestDTO;
import com.guciowons.yummify.auth.exception.AccountExistsByEmailException;
import com.guciowons.yummify.auth.exception.AccountExistsByUsernameException;
import com.guciowons.yummify.auth.mapper.UserRequestMapper;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserCreateService extends AbstractKeycloakService implements PublicUserCreateService {
    private final UserRequestMapper userRequestMapper;

    public UserCreateService(KeycloakAuthClient keycloakAuthClient, KeycloakAdminClient keycloakAdminClient, UserRequestMapper userRequestMapper, SecurePasswordGenerator securePasswordGenerator) {
        super(keycloakAuthClient, keycloakAdminClient, securePasswordGenerator);
        this.userRequestMapper = userRequestMapper;
    }

    public UUID createUserWithPassword(UserRequestDTO userRequest) {
        String adminToken = getAdminToken();
        UserRepresentation user = createAndGetUser(userRequest, adminToken);

        String password = securePasswordGenerator.generate(16);
        keycloakAdminClient.setPassword(user.getId(), adminToken, new PasswordRequestDTO(password));

        return UUID.fromString(user.getId());
    }

    public UUID createUser(UserRequestDTO userRequest) {
        String adminToken = getAdminToken();
        return UUID.fromString(createAndGetUser(userRequest, adminToken).getId());
    }

    private UserRepresentation createAndGetUser(UserRequestDTO userRequest, String adminToken) {
        if (keycloakAdminClient.countUsersByEmail(adminToken, userRequest.getEmail()) > 0) {
            throw new AccountExistsByEmailException("owner/email");
        }

        if (keycloakAdminClient.countUsersByUsername(adminToken, userRequest.getUsername()) > 0) {
            throw new AccountExistsByUsernameException("owner/username");
        }

        keycloakAdminClient.createUser(adminToken, userRequestMapper.toUserRepresentation(userRequest));

        return keycloakAdminClient.getUserByEmail(adminToken, userRequest.getEmail()).getFirst();
    }
}
