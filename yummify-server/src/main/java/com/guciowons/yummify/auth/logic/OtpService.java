package com.guciowons.yummify.auth.logic;

import com.guciowons.yummify.auth.OtpDTO;
import com.guciowons.yummify.auth.PublicOtpService;
import com.guciowons.yummify.auth.client.KeycloakAdminClient;
import com.guciowons.yummify.auth.client.KeycloakAuthClient;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@Service
public class OtpService extends AbstractKeycloakService implements PublicOtpService {
    public OtpService(
            KeycloakAuthClient keycloakAuthClient,
            KeycloakAdminClient keycloakAdminClient,
            SecurePasswordGenerator securePasswordGenerator
    ) {
        super(keycloakAuthClient, keycloakAdminClient, securePasswordGenerator);
    }

    public OtpDTO createOtp(UUID userId) {
        String adminToken = getAdminToken();
        UserRepresentation userResponse = keycloakAdminClient.getUser(userId.toString(), adminToken);

        String otp = securePasswordGenerator.generate(16);
        String otpExpirationDate = LocalDateTime.now().plusMinutes(5).toString();
        userResponse.getAttributes().put("otp", Collections.singletonList(otp));
        userResponse.getAttributes().put("otpExpirationDate", Collections.singletonList(otpExpirationDate));
        keycloakAdminClient.updateUser(userId.toString(), adminToken, userResponse);

        return new OtpDTO(userResponse.getUsername(), otp);
    }
}
