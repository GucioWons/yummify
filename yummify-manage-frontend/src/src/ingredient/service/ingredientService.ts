import axiosInstance from "../../common/api/axiosInstance.ts";
import {DTOs} from "../../common/dtos.ts";
import IngredientListDTO = DTOs.IngredientListDTO;

export const ingredientService = {
    async getIngredients() {
        return axiosInstance.get<IngredientListDTO[]>('ingredients');
    },
}