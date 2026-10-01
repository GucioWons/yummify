import { apiClient } from "./apiClient";
import {RestaurantClientDTO} from "@/store/authStore";

export const getRestaurant = async (): Promise<RestaurantClientDTO> => {
    const response = await apiClient.get<RestaurantClientDTO>("/restaurants");

    return response.data;
};