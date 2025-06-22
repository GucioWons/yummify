package com.guciowons.yummify.auth.logic;

import com.guciowons.yummify.auth.OtpDTO;
import com.guciowons.yummify.auth.PublicAuthService;
import com.guciowons.yummify.auth.UserRequestDTO;
import com.guciowons.yummify.auth.client.*;
import com.guciowons.yummify.auth.exception.AccountExistsByEmailException;
import com.guciowons.yummify.auth.exception.AccountExistsByUsernameException;
import com.guciowons.yummify.auth.mapper.UserRequestMapper;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KeycloakService implements PublicAuthService {
    private static final String GRANT_TYPE = "password";
    private static final String CLIENT_ID = "admin-cli";

    @Value("${spring.security.oauth2.admin.username}")
    private String adminUsername;

    @Value("${spring.security.oauth2.admin.password}")
    private String adminPassword;

    private final SecurePasswordGenerator securePasswordGenerator;
    private final KeycloakAuthClient keycloakAuthClient;
    private final KeycloakAdminClient keycloakAdminClient;
    private final UserRequestMapper userRequestMapper;

    @Override
    public UUID createUserAndGetId(UserRequestDTO userRequest) {
        String adminToken = "Bearer " + getAdminToken().access_token();

        if (keycloakAdminClient.countUsersByEmail(adminToken, userRequest.getEmail()) > 0) {
            throw new AccountExistsByEmailException("owner/email");
        }

        if (keycloakAdminClient.countUsersByUsername(adminToken, userRequest.getUsername()) > 0) {
            throw new AccountExistsByUsernameException("owner/username");
        }

        keycloakAdminClient.createUser(adminToken, userRequestMapper.toUserRepresentation(userRequest));

        UserRepresentation userResponse = keycloakAdminClient.getUserByEmail(adminToken, userRequest.getEmail()).getFirst();
        String password = securePasswordGenerator.generate(16);
        keycloakAdminClient.setPassword(userResponse.getId(), adminToken, new PasswordRequestDTO(password));

        return UUID.fromString(userResponse.getId());
    }

    public OtpDTO createOtp(UUID userId) {
        String adminToken = "Bearer " + getAdminToken().access_token();
        UserRepresentation userResponse = keycloakAdminClient.getUser(userId.toString(), adminToken);

        String otp = securePasswordGenerator.generate(16);
        String otpExpirationDate = LocalDateTime.now().plusMinutes(5).toString();
        userResponse.getAttributes().put("otp", Collections.singletonList(otp));
        userResponse.getAttributes().put("otpExpirationDate", Collections.singletonList(otpExpirationDate));
        keycloakAdminClient.updateUser(userId.toString(), adminToken, userResponse);

        return new OtpDTO(userResponse.getUsername(), otp);
    }

    private AdminTokenResponseDTO getAdminToken() {
        return keycloakAuthClient.getAdminAccessToken(
                new AdminTokenRequestDTO(GRANT_TYPE, CLIENT_ID, adminUsername, adminPassword)
        );
    }
}
