import React, { useState, useEffect } from "react";
import { AuthContext } from "./AuthContext.tsx";
import keycloak from "../config/keycloak.ts";
import type {User} from "../model/User.ts";
import type {KeycloakTokenParsed} from "keycloak-js";

export interface AuthProviderProps {
    children: React.ReactNode;
}

function AuthProvider(props: AuthProviderProps) {
    const {children} = props;

    const [user, setUser] = useState<User | null>(() => {
        const saved = localStorage.getItem("user");
        return saved ? JSON.parse(saved) : null;
    });

    const [token, setToken] = useState<string | null>(() => localStorage.getItem("token"));

    const saveUserAndToken = (tokenParsed: KeycloakTokenParsed, token: string) => {
        const user: User = {
            id: tokenParsed.sub!,
            restaurantId: tokenParsed.restaurantId!,
            email: tokenParsed.email!,
            lastName: tokenParsed.family_name!,
            name: tokenParsed.given_name!,
            username: tokenParsed.preferred_username!,
        };

        setUser(user);
        setToken(token);

        localStorage.setItem("user", JSON.stringify(user));
        localStorage.setItem("token", token);
    }

    useEffect(() => {
        keycloak.init({ onLoad: "check-sso" }).then(authenticated => {
            if (authenticated && keycloak.token && keycloak.tokenParsed) {
                saveUserAndToken(keycloak.tokenParsed, keycloak.token);
            }
        }).catch(err => console.error("Keycloak init error:", err));
    }, []);

    const logout = () => {
        keycloak.logout({redirectUri: window.location.origin}).then(() => {
            setUser(null);
            setToken(null);
            localStorage.removeItem("user");
            localStorage.removeItem("token");
            }
        );
    };

    return (
        <AuthContext.Provider value={{ user, token, saveUserAndToken, logout }}>
            {children}
        </AuthContext.Provider>
    );
}

export default AuthProvider;