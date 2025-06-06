package com.guciowons;

import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.List;

public class OtpAuthenticatorFactory implements AuthenticatorFactory {
    private static final String ID = "otp-auth-with-userid";
    private static final String DISPLAY_TYPE = "OTP Auth (with userId)";
    private static final String HELP_TEXT = "Logs in with userId + fixed OTP (123)";
    private static final String REFERENCE_CATEGORY = "otp";

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public Authenticator create(KeycloakSession session) {
        return new OtpAuthenticator();
    }

    @Override
    public void init(org.keycloak.Config.Scope config) {

    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getDisplayType() {
        return DISPLAY_TYPE;
    }

    @Override
    public String getHelpText() {
        return HELP_TEXT;
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return List.of();
    }

    @Override
    public String getReferenceCategory() {
        return REFERENCE_CATEGORY;
    }

    @Override
    public boolean isConfigurable() {
        return false;
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return new AuthenticationExecutionModel.Requirement[]{
                AuthenticationExecutionModel.Requirement.REQUIRED,
                AuthenticationExecutionModel.Requirement.DISABLED
        };
    }
}
