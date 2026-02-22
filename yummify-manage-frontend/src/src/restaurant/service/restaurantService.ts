import axiosInstance from "../../common/api/axiosInstance.ts";
import {Dtos} from "../../common/dtos.ts";
import RestaurantClientDto = Dtos.RestaurantClientDto;
import RestaurantManageDto = Dtos.RestaurantManageDto;

export const restaurantService = {
    async getRestaurantClient() {
        return axiosInstance.get<RestaurantClientDto>('restaurants');
    },

    async getRestaurantManage() {
        return axiosInstance.get<RestaurantManageDto>('restaurants/manage');
    }
}