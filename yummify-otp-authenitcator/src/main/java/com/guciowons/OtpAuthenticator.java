package com.guciowons;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.mindrot.jbcrypt.BCrypt;

import java.time.Instant;
import java.time.format.DateTimeParseException;

public class OtpAuthenticator implements Authenticator {
    public static final String USERNAME_PARAM = "username";
    public static final String OTP_PARAM = "otp";

    public static final String OTP_ATTRIBUTE = "otp";
    public static final String OTP_EXPIRATION_DATE_ATTRIBUTE = "otp_expiration_date";

    public static final String ERROR_MISSING_CREDENTIALS = "Missing username or one-time password";
    public static final String ERROR_USER_NOT_FOUND = "User not found";
    public static final String ERROR_OTP_NOT_CONFIGURED = "OTP not configured for this user";
    public static final String ERROR_OTP_EXPIRED = "OTP has expired";
    public static final String ERROR_INVALID_OTP = "Invalid one-time password";

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        MultivaluedMap<String, String> params = context.getHttpRequest().getDecodedFormParameters();
        String username = params.getFirst(USERNAME_PARAM);
        String otp = params.getFirst(OTP_PARAM);

        if (isBlank(username) || isBlank(otp)) {
            context.failureChallenge(
                    AuthenticationFlowError.INVALID_CREDENTIALS,
                    createErrorResponse(ERROR_MISSING_CREDENTIALS)
            );
            return;
        }

        UserModel user = getUser(context, username);

        if (user == null) {
            context.failureChallenge(
                    AuthenticationFlowError.INVALID_USER,
                    createErrorResponse(ERROR_USER_NOT_FOUND)
            );
            return;
        }

        String expirationStr = user.getFirstAttribute(OTP_EXPIRATION_DATE_ATTRIBUTE);
        String hashedOtp = user.getFirstAttribute(OTP_ATTRIBUTE);

        if (isBlank(expirationStr) || isBlank(hashedOtp)) {
            context.failureChallenge(
                    AuthenticationFlowError.INVALID_CREDENTIALS,
                    createErrorResponse(ERROR_OTP_NOT_CONFIGURED)
            );
            return;
        }

        if (isOtpExpired(expirationStr)) {
            context.failureChallenge(
                    AuthenticationFlowError.EXPIRED_CODE,
                    createErrorResponse(ERROR_OTP_EXPIRED)
            );
            return;
        }

        if (isOtpInvalid(hashedOtp, otp)) {
            context.failureChallenge(
                    AuthenticationFlowError.INVALID_CREDENTIALS,
                    createErrorResponse(ERROR_INVALID_OTP)
            );
            return;
        }

        clearOtp(user);
        context.setUser(user);
        context.success();
    }

    @Override
    public void action(AuthenticationFlowContext context) {
    }

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        return true;
    }

    @Override
    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {
    }

    @Override
    public void close() {
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private UserModel getUser(AuthenticationFlowContext context, String username) {
        return context.getSession().users().getUserByUsername(context.getRealm(), username);
    }

    private boolean isOtpExpired(String expirationDateString) {
        try {
            Instant expirationDate = Instant.parse(expirationDateString);
            return Instant.now().isAfter(expirationDate);
        } catch (DateTimeParseException e) {
            return true;
        }
    }

    private boolean isOtpInvalid(String hashedOtp, String otp) {
        return !BCrypt.checkpw(otp, hashedOtp);
    }

    private Response createErrorResponse(String errorMessage) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorResponseDTO(errorMessage))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    private void clearOtp(UserModel user) {
        user.removeAttribute(OTP_ATTRIBUTE);
        user.removeAttribute(OTP_EXPIRATION_DATE_ATTRIBUTE);
    }
}
