import axios from "axios";
import * as SecureStore from "expo-secure-store";

export const apiClient = axios.create({
    baseURL: "http://10.0.2.2:9090/api",
    headers: {
        "Content-Type": "application/json",
        "X-Language": "PL",
        "X-Default-Language": "EN"
    },
});

apiClient.interceptors.request.use(async (config) => {
    const token = await SecureStore.getItemAsync("access_token");

    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
});