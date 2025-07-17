package com.guciowons.yummify.auth.logic;

import com.guciowons.yummify.auth.UserRequestDTO;
import com.guciowons.yummify.auth.client.*;
import com.guciowons.yummify.auth.exception.AccountExistsByEmailException;
import com.guciowons.yummify.auth.exception.AccountExistsByUsernameException;
import com.guciowons.yummify.auth.mapper.UserRequestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.representations.idm.UserRepresentation;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserCreateServiceTest {
    @InjectMocks
    private UserCreateService underTest;

    @Mock
    private SecurePasswordGenerator securePasswordGenerator;

    @Mock
    private KeycloakAuthClient keycloakAuthClient;

    @Mock
    private KeycloakAdminClient keycloakAdminClient;

    @Mock
    private UserRequestMapper userRequestMapper;

    private static final String RESTAURANT_ID = UUID.randomUUID().toString();
    private static final String ADMIN_TOKEN = "admin-token";
    private static final String BEARER_ADMIN_TOKEN = "Bearer " + ADMIN_TOKEN;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        setField("adminUsername", "admin");
        setField("adminPassword", "password");
    }

    @Test
    void shouldCreateUser() {
        AdminTokenRequestDTO tokenRequest = buildAdminTokenRequest();

        UserRequestDTO userRequest = buildUserRequest();

        UserRepresentation userRepresentationResponse = new UserRepresentation();
        userRepresentationResponse.setId(UUID.randomUUID().toString());

        UserRepresentation userRepresentationRequest = new UserRepresentation();

        when(keycloakAuthClient.getAdminAccessToken(tokenRequest))
                .thenReturn(new AdminTokenResponseDTO(ADMIN_TOKEN));
        when(keycloakAdminClient.countUsersByEmail(BEARER_ADMIN_TOKEN, "test@example.com"))
                .thenReturn(0);
        when(keycloakAdminClient.countUsersByUsername(BEARER_ADMIN_TOKEN, "test"))
                .thenReturn(0);
        when(keycloakAdminClient.getUserByEmail(BEARER_ADMIN_TOKEN, "test@example.com"))
                .thenReturn(List.of(userRepresentationResponse));
        when(userRequestMapper.toUserRepresentation(userRequest)).thenReturn(userRepresentationRequest);

        UUID result = underTest.createUser(userRequest);

        assertEquals(userRepresentationResponse.getId(), result.toString());
        verify(keycloakAdminClient).createUser(BEARER_ADMIN_TOKEN, userRepresentationRequest);
    }

    @Test
    void shouldCreateUserWithPassword() {
        AdminTokenRequestDTO tokenRequest = buildAdminTokenRequest();

        UserRequestDTO userRequest = buildUserRequest();

        UserRepresentation userRepresentationResponse = new UserRepresentation();
        userRepresentationResponse.setId(UUID.randomUUID().toString());

        UserRepresentation userRepresentationRequest = new UserRepresentation();

        when(keycloakAuthClient.getAdminAccessToken(tokenRequest))
                .thenReturn(new AdminTokenResponseDTO(ADMIN_TOKEN));
        when(keycloakAdminClient.countUsersByEmail(BEARER_ADMIN_TOKEN, "test@example.com"))
                .thenReturn(0);
        when(keycloakAdminClient.countUsersByUsername(BEARER_ADMIN_TOKEN, "test"))
                .thenReturn(0);
        when(keycloakAdminClient.getUserByEmail(BEARER_ADMIN_TOKEN, "test@example.com"))
                .thenReturn(List.of(userRepresentationResponse));
        when(securePasswordGenerator.generate(anyInt())).thenReturn("password");
        when(userRequestMapper.toUserRepresentation(userRequest)).thenReturn(userRepresentationRequest);

        UUID result = underTest.createUserWithPassword(userRequest);

        assertEquals(userRepresentationResponse.getId(), result.toString());
        verify(keycloakAdminClient).createUser(BEARER_ADMIN_TOKEN, userRepresentationRequest);

        ArgumentCaptor<PasswordRequestDTO> captor = ArgumentCaptor.forClass(PasswordRequestDTO.class);
        verify(keycloakAdminClient).setPassword(eq(userRepresentationResponse.getId()), eq(BEARER_ADMIN_TOKEN), captor.capture());
        assertEquals("password", captor.getValue().getValue());
    }

    @Test
    void shouldNotCreateUserWhenEmailIsNotUnique() {
        AdminTokenRequestDTO tokenRequest = buildAdminTokenRequest();

        UserRequestDTO userRequest = buildUserRequest();

        when(keycloakAuthClient.getAdminAccessToken(tokenRequest))
                .thenReturn(new AdminTokenResponseDTO(ADMIN_TOKEN));
        when(keycloakAdminClient.countUsersByEmail(BEARER_ADMIN_TOKEN, "test@example.com"))
                .thenReturn(1);

        assertThrows(AccountExistsByEmailException.class, () -> underTest.createUser(userRequest));

        verify(keycloakAdminClient, never()).countUsersByUsername(any(), any());
        verify(keycloakAdminClient, never()).createUser(any(), any());
        verify(keycloakAdminClient, never()).setPassword(any(), any(), any());
    }


    @Test
    void shouldNotCreateUserWhenUsernameIsNotUnique() {
        AdminTokenRequestDTO tokenRequest = buildAdminTokenRequest();

        UserRequestDTO userRequest = buildUserRequest();

        when(keycloakAuthClient.getAdminAccessToken(tokenRequest))
                .thenReturn(new AdminTokenResponseDTO(ADMIN_TOKEN));
        when(keycloakAdminClient.countUsersByEmail(BEARER_ADMIN_TOKEN, "test@example.com"))
                .thenReturn(0);
        when(keycloakAdminClient.countUsersByUsername(BEARER_ADMIN_TOKEN, "test"))
                .thenReturn(1);

        assertThrows(AccountExistsByUsernameException.class, () -> underTest.createUser(userRequest));

        verify(keycloakAdminClient, never()).createUser(any(), any());
        verify(keycloakAdminClient, never()).setPassword(any(), any(), any());
    }

    private void setField(String name, String value) throws NoSuchFieldException, IllegalAccessException {
        Field passwordField = AbstractKeycloakService.class.getDeclaredField(name);
        passwordField.setAccessible(true);
        passwordField.set(underTest, value);
    }

    private AdminTokenRequestDTO buildAdminTokenRequest() {
        return new AdminTokenRequestDTO("admin", "password");
    }

    private UserRequestDTO buildUserRequest() {
        return new UserRequestDTO(
                "test@example.com",
                "test",
                "Jane",
                "Doe",
                Map.of("restaurantId", List.of(RESTAURANT_ID))
        );
    }
}