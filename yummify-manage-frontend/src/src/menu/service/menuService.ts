import axiosInstance from "../../common/api/axiosInstance.ts";
import {Dtos} from "../../common/dtos.ts";
import MenuVersionClientDto = Dtos.MenuVersionClientDto;
import MenuVersionManageDto = Dtos.MenuVersionManageDto;

export const menuService = {
    async getPublishedMenuVersion() {
        return axiosInstance.get<MenuVersionClientDto>('menu/versions/published');
    },

    async getDraftMenuVersion() {
        return axiosInstance.get<MenuVersionManageDto>('menu/versions/draft');
    },
}