import axiosInstance from "../../common/api/axiosInstance.ts";
import {Dtos} from "../../common/dtos.ts";
import IngredientClientDto = Dtos.IngredientClientDto;
import IngredientManageDto = Dtos.IngredientManageDto;

export const ingredientService = {
    async getIngredients() {
        return axiosInstance.get<IngredientClientDto[]>('ingredients');
    },

    async createIngredient(data: IngredientManageDto) {
        return axiosInstance.post<IngredientManageDto, IngredientManageDto>('ingredients', data);
    },

    async getIngredient(id: string) {
        return axiosInstance.get<IngredientManageDto>(`ingredients/${id}`);
    },
}