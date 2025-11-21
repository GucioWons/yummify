import React, { useState, useEffect } from "react";
import { AuthContext } from "./AuthContext";
import keycloak from "./keycloak";
import type {User} from "./User.ts";


export interface AuthProviderProps {
    children: React.ReactNode;
}

const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
    const [user, setUser] = useState<User | null>(() => {
        const saved = localStorage.getItem("user");
        return saved ? JSON.parse(saved) : null;
    });

    const [token, setToken] = useState<string | null>(() => localStorage.getItem("token"));

    useEffect(() => {
        keycloak.init({ onLoad: "check-sso" }).then(authenticated => {
            if (authenticated && keycloak.token && keycloak.tokenParsed) {
                setUser({
                    id: keycloak.tokenParsed.sub!,
                    restaurantId: keycloak.tokenParsed.restaurantId!,
                    email: keycloak.tokenParsed.email!,
                    lastName: keycloak.tokenParsed.family_name!,
                    name: keycloak.tokenParsed.given_name!,
                    username: keycloak.tokenParsed.preferred_username!,
                });
                setToken(keycloak.token);

                localStorage.setItem("user", JSON.stringify(keycloak.tokenParsed));
                localStorage.setItem("token", keycloak.token);
            }
        }).catch(err => console.error("Keycloak init error:", err));
    }, []);

    return (
        <AuthContext.Provider value={{ user, token, setUser, setToken }}>
            {children}
        </AuthContext.Provider>
    );
};


export default AuthProvider;