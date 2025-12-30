import axiosInstance from "../../common/api/axiosInstance.ts";
import {DTOs} from "../../common/dtos.ts";
import DishManageDTO = DTOs.DishManageDTO;
import DishImageUrlDTO = DTOs.DishImageUrlDTO;
import DishListDTO = DTOs.DishListDTO;

export const dishService = {
    async getDishes() {
        return axiosInstance.get<DishListDTO[]>('dishes');
    },

    async createDish(data: DishManageDTO) {
        return axiosInstance.post<DishManageDTO>('dishes', data);
    },

    async updateImage(id: string, image: File) {
        const formData = new FormData();
        formData.append("image", image);

        const headers = {
            headers: {
                "Content-Type": "multipart/form-data",
            }
        };

        return axiosInstance.put<DishImageUrlDTO>(`dishes/${id}/image`, formData, headers);
    }
}