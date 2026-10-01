import axios from "axios";

const KEYCLOAK_URL = "http://10.0.2.2:8080/realms/yummify/protocol/openid-connect/token";

interface LoginResponse {
    access_token: string;
    expires_in: number;
    refresh_token?: string;
    refresh_expires_in?: number;
    token_type: string;
}

interface LoginCredentials {
    username: string;
    otp: string;
}

export const login = async (credentials: LoginCredentials): Promise<LoginResponse> => {
    const response = await axios.post<LoginResponse>(
        KEYCLOAK_URL,
        new URLSearchParams({
            grant_type: "password",
            client_id: "device-client",
            username: credentials.username,
            otp: credentials.otp,
        }).toString(),
        {
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
        },
    );

    return response.data;
};