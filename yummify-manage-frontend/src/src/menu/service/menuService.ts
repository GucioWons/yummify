import axiosInstance from "../../common/api/axiosInstance.ts";
import {Dtos, UpdateMenuSectionNameRequest} from "../../common/dtos.ts";
import MenuVersionClientDto = Dtos.MenuVersionClientDto;
import MenuVersionManageDto = Dtos.MenuVersionManageDto;
import MenuSectionManageDto = Dtos.MenuSectionManageDto;

export const menuService = {
    async getPublishedMenuVersion() {
        return axiosInstance.get<MenuVersionClientDto>('menu/versions/published');
    },

    async getDraftMenuVersion() {
        return axiosInstance.get<MenuVersionManageDto>('menu/versions/draft');
    },

    async updateMenuSectionName(sectionId: string, data: UpdateMenuSectionNameRequest) {
        return axiosInstance.patch<UpdateMenuSectionNameRequest, MenuSectionManageDto>(
            `menu/versions/sections/${sectionId}/name`,
            data
        );
    }
}