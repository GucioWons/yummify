import axiosInstance from "../../common/api/axiosInstance.ts";
import {DTOs} from "../../common/dtos.ts";
import IngredientListDTO = DTOs.IngredientListDTO;
import IngredientManageDTO = DTOs.IngredientManageDTO;

export const ingredientService = {
    async getIngredients() {
        return axiosInstance.get<IngredientListDTO[]>('ingredients');
    },

    async createIngredient(data: IngredientManageDTO) {
        return axiosInstance.post<IngredientManageDTO, IngredientManageDTO>('ingredients', data);
    }
}