import {apiClient} from "@/api/apiClient";
import {DishClientDTO} from "@/types/dish";

export const getDishes = async (): Promise<DishClientDTO[]> => {
    const response = await apiClient.get<DishClientDTO[]>("/client/dishes");

    return response.data;
};