package com.guciowons;

import jakarta.ws.rs.core.MultivaluedMap;
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

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        MultivaluedMap<String, String> params = context.getHttpRequest().getDecodedFormParameters();
        String username = params.getFirst(USERNAME_PARAM);
        String otp = params.getFirst(OTP_PARAM);

        if (isBlank(username) || isBlank(otp)) {
            context.failure(AuthenticationFlowError.INVALID_CREDENTIALS);
            return;
        }

        UserModel user = getUser(context, username);

        if (user == null) {
            context.failure(AuthenticationFlowError.INVALID_USER);
            return;
        }

        System.out.println("Is otp expired: " + isOtpExpired(user));
        if (isOtpExpired(user)) {
            context.failure(AuthenticationFlowError.EXPIRED_CODE);
            return;
        }

        System.out.println("Is otp valid: " + isOtpValid(user, otp));
        if (!isOtpValid(user, otp)) {
            context.setUser(user);
            context.failure(AuthenticationFlowError.INVALID_CREDENTIALS);
            System.out.println("Twoja stara 69");
            return;
        }

        System.out.println("Twoja stara 1");
        clearOtp(user);
        System.out.println("Twoja stara 2");
        context.setUser(user);
        System.out.println("Twoja stara 3");
        context.success();
        System.out.println("Twoja stara 3");
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

    private boolean isOtpExpired(UserModel user) {
        String expirationStr = user.getFirstAttribute(OTP_EXPIRATION_DATE_ATTRIBUTE);
        if (expirationStr == null) return true;

        try {
            Instant expiration = Instant.parse(expirationStr);
            return Instant.now().isAfter(expiration);
        } catch (DateTimeParseException e) {
            return true;
        }
    }

    private boolean isOtpValid(UserModel user, String otp) {
        String hashedOtp = user.getFirstAttribute(OTP_ATTRIBUTE);
        System.out.println("OTP: " + otp);
        System.out.println("Hashed OTP: " + hashedOtp);
        if (hashedOtp == null) return false;

        try {
            return BCrypt.checkpw(otp, hashedOtp);
        } catch (Exception e) {
            System.out.println("BCrypt error: " + e.getMessage());
            return false;
        }
    }

    private void clearOtp(UserModel user) {
        user.removeAttribute(OTP_ATTRIBUTE);
        user.removeAttribute(OTP_EXPIRATION_DATE_ATTRIBUTE);
    }
}
