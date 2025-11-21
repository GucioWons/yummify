import {createContext} from "react";
import type {User} from "../model/User.ts";
import type {KeycloakTokenParsed} from "keycloak-js";

interface AuthContextType {
    user: User | null;
    token: string | null;
    saveUserAndToken: (user: KeycloakTokenParsed, token: string) => void;
    logout: () => void;
}


export const AuthContext = createContext<AuthContextType>({
    user: null,
    token: null,
    saveUserAndToken: () => {},
    logout: () => {},
});