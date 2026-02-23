import axiosInstance from "../../common/api/axiosInstance.ts";
import {Dtos} from "../../common/dtos.ts";
import DishManageDto = Dtos.DishManageDto;
import DishImageUrlDto = Dtos.DishImageUrlDto;
import DishListDto = Dtos.DishListDto;

export const dishService = {
    async createDish(data: DishManageDto) {
        return axiosInstance.post<DishManageDto>('dishes', data);
    },

    async getDishes() {
        return axiosInstance.get<DishListDto[]>('dishes');
    },

    async getDish(id: string) {
        return axiosInstance.get<DishManageDto>(`dishes/${id}`);
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