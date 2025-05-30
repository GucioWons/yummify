package com.guciowons;

import jakarta.ws.rs.core.MultivaluedMap;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

public class OTPAuthenticator implements Authenticator {
    @Override
    public void authenticate(AuthenticationFlowContext context) {
        MultivaluedMap<String, String> params = context.getHttpRequest().getDecodedFormParameters();
        String username = params.getFirst("username");
        String otp = params.getFirst("otp");

        if (username == null || otp == null) {
            context.failure(AuthenticationFlowError.INVALID_CREDENTIALS);
            return;
        }

        UserModel user = context.getSession().users().getUserByUsername(context.getRealm(), username);

        if (user == null) {
            context.failure(AuthenticationFlowError.INVALID_USER);
            return;
        }

        boolean valid = otp.equals("123");

        if (valid) {
            context.setUser(user);
            context.success();
        } else {
            context.failure(AuthenticationFlowError.INVALID_CREDENTIALS);
        }
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
}
