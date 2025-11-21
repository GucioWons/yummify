import {useCallback, useContext} from "react";
import keycloak from "../config/keycloak.ts";
import {AuthContext} from "../context/AuthContext.tsx";

function LoginPage() {
    const { saveUserAndToken } = useContext(AuthContext);

    const handleLogin = useCallback(() => {
        keycloak.login().then(() => {
            if (keycloak.token && keycloak.tokenParsed) {
                saveUserAndToken(keycloak.tokenParsed, keycloak.token)
            }
        });
    }, [saveUserAndToken]);


    return (
        <button onClick={handleLogin}>Login</button>
    )
}

export default LoginPage;