import axiosInstance from "../../common/api/axiosInstance.ts";
import {Dtos} from "../../common/dtos.ts";
import DishManageDto = Dtos.DishManageDto;
import DishImageUrlDto = Dtos.DishImageUrlDto;

export const dishService = {
    async createDish(data: DishManageDto) {
        return axiosInstance.post<DishManageDto>('dishes', data);
    },

    async updateImage(id: string, image: File) {
        const formData = new FormData();
        formData.append("image", image);

        const headers = {
            headers: {
                "Content-Type": "multipart/form-data",
            }
        };

        return axiosInstance.put<DishImageUrlDto>(`dishes/${id}/image`, formData, headers);
    }
}