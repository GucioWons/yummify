import axiosInstance from "../../common/api/axiosInstance.ts";
import {Dtos} from "../../common/dtos.ts";
import MenuVersionManageDto = Dtos.MenuVersionManageDto;
import MenuSectionManageDto = Dtos.MenuSectionManageDto;
import CreateMenuSectionRequest = Dtos.CreateMenuSectionRequest;
import UpdateMenuSectionNameRequest = Dtos.UpdateMenuSectionNameRequest;
import MenuEntryDto = Dtos.MenuEntryDto;

export const menuService = {
    async getPublishedMenuVersion() {
        return axiosInstance.get<MenuVersionManageDto>('menu-versions/published');
    },

    async getDraftMenuVersion() {
        return axiosInstance.get<MenuVersionManageDto>('menu-versions/draft');
    },

    async createMenuSection(data: CreateMenuSectionRequest) {
        return axiosInstance.post<CreateMenuSectionRequest, MenuSectionManageDto>('menu-versions/sections', data);
    },

    async updateMenuSectionName(sectionId: string, data: UpdateMenuSectionNameRequest) {
        return axiosInstance.patch<UpdateMenuSectionNameRequest, MenuSectionManageDto>(
            `menu-versions/sections/${sectionId}/name`,
            data
        );
    },

    async createMenuEntry(sectionId: string, data: MenuEntryDto) {
        return axiosInstance.post<MenuEntryDto, MenuEntryDto>(`menu-versions/sections/${sectionId}/entries`, data);
    }
}