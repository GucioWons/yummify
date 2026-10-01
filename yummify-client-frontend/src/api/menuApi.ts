import {OrderClientDTO} from "@/types/order";
import {apiClient} from "@/api/apiClient";
import {MenuVersionClientDTO} from "@/types/menu";

export const getMenu = async (): Promise<MenuVersionClientDTO> => {
    const response = await apiClient.get<MenuVersionClientDTO>("/client/menu-versions/published");

    return response.data;
};