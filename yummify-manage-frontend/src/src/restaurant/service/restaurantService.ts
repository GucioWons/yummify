import axiosInstance from "../../common/api/axiosInstance.ts";
import {DTOs} from "../../common/dtos.ts";
import RestaurantClientDTO = DTOs.RestaurantClientDTO;
import RestaurantManageDTO = DTOs.RestaurantManageDTO;

export const restaurantService = {
    async getRestaurantClient() {
        return axiosInstance.get<RestaurantClientDTO>('restaurants');
    },

    async getRestaurantManage() {
        return axiosInstance.get<RestaurantManageDTO>('restaurants/manage');
    }
}