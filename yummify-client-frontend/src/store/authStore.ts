import { create } from "zustand";

export interface RestaurantClientDTO {
    id: string;
    name: string;
}

interface AuthState {
    accessToken: string | null;
    restaurant: RestaurantClientDTO | null;

    setSession: (
        accessToken: string,
        restaurant: RestaurantClientDTO,
    ) => void;

    clearSession: () => void;
}

export const useAuthStore = create<AuthState>((set) => ({
    accessToken: null,
    restaurant: null,

    setSession: (accessToken, restaurant) =>
        set({
            accessToken,
            restaurant,
        }),

    clearSession: () =>
        set({
            accessToken: null,
            restaurant: null,
        }),
}));