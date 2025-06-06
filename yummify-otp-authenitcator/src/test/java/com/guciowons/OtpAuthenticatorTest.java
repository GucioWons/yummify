package com.guciowons;

import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;
import org.keycloak.http.HttpRequest;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.UserProvider;
import org.mindrot.jbcrypt.BCrypt;

import java.time.Instant;

import static com.guciowons.OtpAuthenticator.*;
import static org.mockito.Mockito.*;

class OtpAuthenticatorTest {
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String WRONG_PASSWORD = "wrong_password";

    private static final String PAST_EXPIRATION_DATE = Instant.now().minusSeconds(3600).toString();
    private static final String FUTURE_EXPIRATION_DATE = Instant.now().plusSeconds(3600).toString();

    private static final String HASHED_PASSWORD = BCrypt.hashpw(PASSWORD, BCrypt.gensalt());

    private OtpAuthenticator authenticator;
    private AuthenticationFlowContext context;
    private MultivaluedMap<String, String> formParams;
    private KeycloakSession session;
    private RealmModel realm;
    private UserModel user;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        authenticator = new OtpAuthenticator();
        context = mock(AuthenticationFlowContext.class);
        formParams = mock(MultivaluedMap.class);
        session = mock(KeycloakSession.class);
        realm = mock(RealmModel.class);
        user = mock(UserModel.class);

        HttpRequest httpRequest = mock(HttpRequest.class);

        when(context.getHttpRequest()).thenReturn(httpRequest);
        when(httpRequest.getDecodedFormParameters()).thenReturn(formParams);
        when(context.getSession()).thenReturn(session);
        when(context.getRealm()).thenReturn(realm);
    }

    @Test
    @DisplayName("Should authenticate")
    void shouldAuthenticate() {
        when(formParams.getFirst(USERNAME_PARAM)).thenReturn(USERNAME);
        when(formParams.getFirst(OTP_PARAM)).thenReturn(PASSWORD);
        mockUser(user);

        when(user.getFirstAttribute(OTP_ATTRIBUTE)).thenReturn(HASHED_PASSWORD);
        when(user.getFirstAttribute(OTP_EXPIRATION_DATE_ATTRIBUTE)).thenReturn(FUTURE_EXPIRATION_DATE);

        authenticator.authenticate(context);

        verify(context).setUser(user);
        verify(context).success();
        verify(user).removeAttribute(OTP_ATTRIBUTE);
        verify(user).removeAttribute(OTP_EXPIRATION_DATE_ATTRIBUTE);
    }

    @Test
    @DisplayName("Should not authenticate when username param is null")
    void shouldNotAuthenticateWhenUsernameParamIsNull() {
        when(formParams.getFirst(USERNAME_PARAM)).thenReturn(null);
        when(formParams.getFirst(OTP_PARAM)).thenReturn(PASSWORD);

        authenticator.authenticate(context);

        verify(context).failureChallenge(eq(AuthenticationFlowError.INVALID_CREDENTIALS), any(Response.class));
    }

    @Test
    @DisplayName("Should not authenticate when username param is blank")
    void shouldNotAuthenticateWhenUsernameParamIsBlank() {
        when(formParams.getFirst(USERNAME_PARAM)).thenReturn("");
        when(formParams.getFirst(OTP_PARAM)).thenReturn(PASSWORD);

        authenticator.authenticate(context);

        verify(context).failureChallenge(eq(AuthenticationFlowError.INVALID_CREDENTIALS), any(Response.class));
    }

    @Test
    @DisplayName("Should not authenticate when otp param is null")
    void shouldNotAuthenticateWhenOtpParamIsNull() {
        when(formParams.getFirst(USERNAME_PARAM)).thenReturn(USERNAME);
        when(formParams.getFirst(OTP_PARAM)).thenReturn(null);

        authenticator.authenticate(context);

        verify(context).failureChallenge(eq(AuthenticationFlowError.INVALID_CREDENTIALS), any(Response.class));
    }

    @Test
    @DisplayName("Should not authenticate when otp param is blank")
    void shouldNotAuthenticateWhenOtpParamIsBlank() {
        when(formParams.getFirst(USERNAME_PARAM)).thenReturn(USERNAME);
        when(formParams.getFirst(OTP_PARAM)).thenReturn("");

        authenticator.authenticate(context);

        verify(context).failureChallenge(eq(AuthenticationFlowError.INVALID_CREDENTIALS), any(Response.class));
    }

    @Test
    @DisplayName("Should not authenticate when user not found")
    void shouldNotAuthenticateWhenUserNotFound() {
        when(formParams.getFirst(USERNAME_PARAM)).thenReturn(USERNAME);
        when(formParams.getFirst(OTP_PARAM)).thenReturn(PASSWORD);
        mockUser(null);

        authenticator.authenticate(context);

        verify(context).failureChallenge(eq(AuthenticationFlowError.INVALID_USER), any(Response.class));
    }

    @Test
    @DisplayName("Should not authenticate when otp expiration date attribute is null")
    void shouldNotAuthenticateWhenOtpExpirationDateAttributeIsNull() {
        when(formParams.getFirst(USERNAME_PARAM)).thenReturn(USERNAME);
        when(formParams.getFirst(OTP_PARAM)).thenReturn("password");
        mockUser(user);

        when(user.getFirstAttribute(OTP_ATTRIBUTE)).thenReturn(HASHED_PASSWORD);
        when(user.getFirstAttribute(OTP_EXPIRATION_DATE_ATTRIBUTE)).thenReturn(null);

        authenticator.authenticate(context);

        verify(context).failureChallenge(eq(AuthenticationFlowError.INVALID_CREDENTIALS), any(Response.class));
    }

    @Test
    @DisplayName("Should not authenticate when otp expiration date attribute is blank")
    void shouldNotAuthenticateWhenOtpExpirationDateAttributeIsBlank() {
        when(formParams.getFirst(USERNAME_PARAM)).thenReturn(USERNAME);
        when(formParams.getFirst(OTP_PARAM)).thenReturn(PASSWORD);
        mockUser(user);

        when(user.getFirstAttribute(OTP_ATTRIBUTE)).thenReturn(HASHED_PASSWORD);
        when(user.getFirstAttribute(OTP_EXPIRATION_DATE_ATTRIBUTE)).thenReturn("");

        authenticator.authenticate(context);

        verify(context).failureChallenge(eq(AuthenticationFlowError.INVALID_CREDENTIALS), any(Response.class));
    }

    @Test
    @DisplayName("Should not authenticate when otp attribute is null")
    void shouldNotAuthenticateWhenOtpAttributeIsNull() {
        when(formParams.getFirst(USERNAME_PARAM)).thenReturn(USERNAME);
        when(formParams.getFirst(OTP_PARAM)).thenReturn(PASSWORD);
        mockUser(user);

        when(user.getFirstAttribute(OTP_ATTRIBUTE)).thenReturn(null);
        when(user.getFirstAttribute(OTP_EXPIRATION_DATE_ATTRIBUTE)).thenReturn(FUTURE_EXPIRATION_DATE);

        authenticator.authenticate(context);

        verify(context).failureChallenge(eq(AuthenticationFlowError.INVALID_CREDENTIALS), any(Response.class));
    }

    @Test
    @DisplayName("Should not authenticate when otp attribute is blank")
    void shouldNotAuthenticateWhenOtpAttributeIsBlank() {
        when(formParams.getFirst(USERNAME_PARAM)).thenReturn(USERNAME);
        when(formParams.getFirst(OTP_PARAM)).thenReturn(PASSWORD);
        mockUser(user);

        when(user.getFirstAttribute(OTP_ATTRIBUTE)).thenReturn("");
        when(user.getFirstAttribute(OTP_EXPIRATION_DATE_ATTRIBUTE)).thenReturn(FUTURE_EXPIRATION_DATE);

        authenticator.authenticate(context);

        verify(context).failureChallenge(eq(AuthenticationFlowError.INVALID_CREDENTIALS), any(Response.class));
    }

    @Test
    @DisplayName("Should not authenticate when otp expired")
    void shouldNotAuthenticateWhenOtpExpired() {
        when(formParams.getFirst(USERNAME_PARAM)).thenReturn(USERNAME);
        when(formParams.getFirst(OTP_PARAM)).thenReturn(PASSWORD);
        mockUser(user);

        when(user.getFirstAttribute(OTP_ATTRIBUTE)).thenReturn(HASHED_PASSWORD);
        when(user.getFirstAttribute(OTP_EXPIRATION_DATE_ATTRIBUTE)).thenReturn(PAST_EXPIRATION_DATE);

        authenticator.authenticate(context);

        verify(context).failureChallenge(eq(AuthenticationFlowError.EXPIRED_CODE), any(Response.class));
    }

    @Test
    @DisplayName("Should not authenticate when otp expiration date is not valid")
    void shouldNotAuthenticateWhenOtpExpirationDateIsNotValid() {
        when(formParams.getFirst(USERNAME_PARAM)).thenReturn(USERNAME);
        when(formParams.getFirst(OTP_PARAM)).thenReturn(PASSWORD);
        mockUser(user);

        when(user.getFirstAttribute(OTP_ATTRIBUTE)).thenReturn(HASHED_PASSWORD);
        when(user.getFirstAttribute(OTP_EXPIRATION_DATE_ATTRIBUTE)).thenReturn("Definitely not date");

        authenticator.authenticate(context);

        verify(context).failureChallenge(eq(AuthenticationFlowError.EXPIRED_CODE), any(Response.class));
    }

    @Test
    @DisplayName("Should not authenticate when otp is not valid")
    void shouldNotAuthenticateWhenOtpIsNotValid() {
        when(formParams.getFirst(USERNAME_PARAM)).thenReturn(USERNAME);
        when(formParams.getFirst(OTP_PARAM)).thenReturn(WRONG_PASSWORD);
        mockUser(user);

        when(user.getFirstAttribute(OTP_ATTRIBUTE)).thenReturn(HASHED_PASSWORD);
        when(user.getFirstAttribute(OTP_EXPIRATION_DATE_ATTRIBUTE)).thenReturn(FUTURE_EXPIRATION_DATE);

        authenticator.authenticate(context);

        verify(context).failureChallenge(eq(AuthenticationFlowError.INVALID_CREDENTIALS), any(Response.class));
    }

    private void mockUser(UserModel user) {
        UserProvider userProvider = mock(UserProvider.class);
        when(session.users()).thenReturn(userProvider);
        when(userProvider.getUserByUsername(realm, USERNAME)).thenReturn(user);
    }
}