import {useCallback, useContext} from "react";
import keycloak from "../config/keycloak.ts";
import {AuthContext} from "../context/AuthContext.tsx";
import {useTranslation} from "react-i18next";
import i18n from "i18next";

function LoginPage() {
    const { saveUserAndToken } = useContext(AuthContext);

    const { t } = useTranslation();

    const handleLogin = useCallback(() => {
        keycloak.login({locale: i18n.language}).then(() => {
            if (keycloak.token && keycloak.tokenParsed) {
                saveUserAndToken(keycloak.tokenParsed, keycloak.token)
            }
        });
    }, [saveUserAndToken]);


    return (
        <button onClick={handleLogin}>{t("login")}</button>
    )
}

export default LoginPage;