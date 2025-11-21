import {createContext} from "react";

interface AuthContextType {
    user: unknown | null;
    token: string | null;
    setUser: (user: unknown | null) => void;
    setToken: (token: string | null) => void;
}


export const AuthContext = createContext<AuthContextType>({
    user: null,
    token: null,
    setUser: () => {},
    setToken: () => {},
});