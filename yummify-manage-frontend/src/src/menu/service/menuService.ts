import axiosInstance from "../../common/api/axiosInstance.ts";
import {Dtos} from "../../common/dtos.ts";
import MenuVersionClientDto = Dtos.MenuVersionClientDto;

export const menuService = {
    async getPublishedMenuVersion() {
        return axiosInstance.get<MenuVersionClientDto>('menu/versions/published');
    },
}