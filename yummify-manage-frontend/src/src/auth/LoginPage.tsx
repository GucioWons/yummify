import {useCallback, useContext} from "react";
import keycloak from "./keycloak.ts";
import {AuthContext} from "./AuthContext.tsx";

function LoginPage() {
    const { setUser, setToken } = useContext(AuthContext);

    const handleLogin = useCallback(() => {
        keycloak.login().then(() => {
            if (keycloak.token && keycloak.tokenParsed) {
                setUser(keycloak.tokenParsed);
                setToken(keycloak.token);

                localStorage.setItem("user", JSON.stringify(keycloak.tokenParsed));
                localStorage.setItem("token", keycloak.token);
            }
        });
    }, [setUser, setToken]);


    return (
        <button onClick={handleLogin}>Login</button>
    )
}

export default LoginPage;