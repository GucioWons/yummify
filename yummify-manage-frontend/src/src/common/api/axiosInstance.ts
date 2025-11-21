import axios, {AxiosHeaders} from "axios";
import keycloak from "../../auth/config/keycloak.ts";

const axiosInstance = axios.create({
    baseURL: import.meta.env.VITE_API_URL
});

axiosInstance.interceptors.request.use(async (config) => {
    if (keycloak.token) {
        await keycloak.updateToken(30);

        config.headers = new AxiosHeaders(config.headers);
        config.headers.set("Authorization", `Bearer ${keycloak.token}`);
    }
    return config;
}, (error) => {
    return Promise.reject(error);
});

export default axiosInstance;