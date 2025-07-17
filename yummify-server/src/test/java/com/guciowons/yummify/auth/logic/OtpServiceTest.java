package com.guciowons.yummify.auth.logic;

import com.guciowons.yummify.auth.OtpDTO;
import com.guciowons.yummify.auth.client.AdminTokenRequestDTO;
import com.guciowons.yummify.auth.client.AdminTokenResponseDTO;
import com.guciowons.yummify.auth.client.KeycloakAdminClient;
import com.guciowons.yummify.auth.client.KeycloakAuthClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.representations.idm.UserRepresentation;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OtpServiceTest {
    @InjectMocks
    private OtpService underTest;

    @Mock
    private SecurePasswordGenerator securePasswordGenerator;

    @Mock
    private KeycloakAuthClient keycloakAuthClient;

    @Mock
    private KeycloakAdminClient keycloakAdminClient;

    private static final UUID USER_ID = UUID.randomUUID();
    private static final String ADMIN_TOKEN = "admin-token";

    private static final String BEARER_ADMIN_TOKEN = "Bearer " + ADMIN_TOKEN;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        setField("adminUsername", "admin");
        setField("adminPassword", "password");
    }

    @Test
    void shouldGenerateOtpAndUpdateUserAttributes() {
        AdminTokenRequestDTO tokenRequest = buildAdminTokenRequest();
        UserRepresentation user = buildUserRepresentation();

        when(keycloakAuthClient.getAdminAccessToken(tokenRequest))
                .thenReturn(new AdminTokenResponseDTO(ADMIN_TOKEN));
        when(keycloakAdminClient.getUser(USER_ID.toString(), BEARER_ADMIN_TOKEN)).thenReturn(user);
        when(securePasswordGenerator.generate(16)).thenReturn("123456");

        // when
        OtpDTO result = underTest.createOtp(USER_ID);

        // then
        assertEquals("jane.doe", result.username());
        assertEquals("123456", result.otp());

        ArgumentCaptor<UserRepresentation> userCaptor = ArgumentCaptor.forClass(UserRepresentation.class);
        verify(keycloakAdminClient).updateUser(eq(USER_ID.toString()), eq(BEARER_ADMIN_TOKEN), userCaptor.capture());

        UserRepresentation updatedUser = userCaptor.getValue();
        assertTrue(updatedUser.getAttributes().containsKey("otp"));
        assertTrue(updatedUser.getAttributes().containsKey("otpExpirationDate"));
        assertEquals(List.of("123456"), updatedUser.getAttributes().get("otp"));
    }

    private void setField(String name, String value) throws NoSuchFieldException, IllegalAccessException {
        Field passwordField = AbstractKeycloakService.class.getDeclaredField(name);
        passwordField.setAccessible(true);
        passwordField.set(underTest, value);
    }

    private AdminTokenRequestDTO buildAdminTokenRequest() {
        return new AdminTokenRequestDTO("admin", "password");
    }

    private UserRepresentation buildUserRepresentation() {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setId(USER_ID.toString());
        userRepresentation.setUsername("jane.doe");
        userRepresentation.setAttributes(new HashMap<>());
        return userRepresentation;
    }

}
