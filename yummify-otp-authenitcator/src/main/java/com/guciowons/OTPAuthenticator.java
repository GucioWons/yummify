package com.guciowons;

import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

public class OTPAuthenticator implements Authenticator {
    @Override
    public void authenticate(AuthenticationFlowContext context) {
        context.challenge(Response.status(Response.Status.UNAUTHORIZED).entity("OTP required").build());
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        MultivaluedMap<String, String> form = context.getHttpRequest().getDecodedFormParameters();
        String otp = form.getFirst("otp");
        String userId = form.getFirst("user");

        if ("123".equals(otp) && userId != null) {
            UserModel user = context.getSession().users().getUserById(context.getRealm(), userId);
            if (user != null) {
                context.setUser(user);
                context.success();
                return;
            }
        }

        context.failureChallenge(
                AuthenticationFlowError.INVALID_CREDENTIALS,
                Response.status(Response.Status.UNAUTHORIZED).entity("Invalid OTP or user").build()
        );
    }

    @Override
    public void close() {

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
}
