package com.guciowons.yummify.auth.logic;

import com.guciowons.yummify.auth.UserRequestDTO;
import com.guciowons.yummify.auth.UserResponseDTO;
import com.guciowons.yummify.auth.client.*;
import com.guciowons.yummify.auth.exception.AccountExistsByEmailException;
import com.guciowons.yummify.auth.exception.AccountExistsByUsernameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class KeycloakServiceTest {
    @InjectMocks
    private KeycloakService underTest;

    @Mock
    private SecurePasswordGenerator securePasswordGenerator;

    @Mock
    private KeycloakAuthClient keycloakAuthClient;

    @Mock
    private KeycloakAdminClient keycloakAdminClient;

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

        UserRequestDTO userRequest = new UserRequestDTO(
                "test@example.com",
                "test",
                "Jane",
                "Doe",
                Map.of("restaurantId", RESTAURANT_ID));

        UserResponseDTO userResponse = new UserResponseDTO(UUID.randomUUID());

        when(keycloakAuthClient.getAdminAccessToken(tokenRequest))
                .thenReturn(new AdminTokenResponseDTO(ADMIN_TOKEN));
        when(keycloakAdminClient.countUsersByEmail(BEARER_ADMIN_TOKEN, "test@example.com"))
                .thenReturn(0);
        when(keycloakAdminClient.countUsersByUsername(BEARER_ADMIN_TOKEN, "test"))
                .thenReturn(0);
        when(keycloakAdminClient.getUserByEmail(BEARER_ADMIN_TOKEN, "test@example.com"))
                .thenReturn(List.of(userResponse));
        when(securePasswordGenerator.generate(anyInt())).thenReturn("password");

        UUID result = underTest.createUserAndGetId(userRequest);

        assertEquals(userResponse.id(), result);
        verify(keycloakAdminClient).createUser(BEARER_ADMIN_TOKEN, userRequest);

        ArgumentCaptor<PasswordRequestDTO> captor = ArgumentCaptor.forClass(PasswordRequestDTO.class);
        verify(keycloakAdminClient).setPassword(eq(userResponse.id().toString()), eq(BEARER_ADMIN_TOKEN), captor.capture());
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

        assertThrows(AccountExistsByEmailException.class, () -> underTest.createUserAndGetId(userRequest));

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

        assertThrows(AccountExistsByUsernameException.class, () -> underTest.createUserAndGetId(userRequest));

        verify(keycloakAdminClient, never()).createUser(any(), any());
        verify(keycloakAdminClient, never()).setPassword(any(), any(), any());
    }

    private void setField(String name, String value) throws NoSuchFieldException, IllegalAccessException {
        Field passwordField = KeycloakService.class.getDeclaredField(name);
        passwordField.setAccessible(true);
        passwordField.set(underTest, value);
    }

    private AdminTokenRequestDTO buildAdminTokenRequest() {
        return new AdminTokenRequestDTO("password", "admin-cli", "admin", "password");
    }

    private UserRequestDTO buildUserRequest() {
        return new UserRequestDTO(
                "test@example.com",
                "test",
                "Jane",
                "Doe",
                Map.of("restaurantId", RESTAURANT_ID)
        );
    }
}