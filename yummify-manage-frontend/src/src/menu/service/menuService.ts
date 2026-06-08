import axiosInstance from "../../common/api/axiosInstance.ts";
import {Dtos} from "../../common/dtos.ts";
import MenuVersionManageDto = Dtos.MenuVersionManageDto;
import MenuSectionManageDto = Dtos.MenuSectionManageDto;
import CreateMenuSectionRequest = Dtos.CreateMenuSectionRequest;
import UpdateMenuSectionNameRequest = Dtos.UpdateMenuSectionNameRequest;
import MenuEntryDto = Dtos.MenuEntryDto;
import MenuVersionArchivedListDto = Dtos.MenuVersionArchivedListDto;

export const menuService = {
    async getPublishedMenuVersion() {
        return axiosInstance.get<MenuVersionManageDto>('menu-versions/published');
    },

    async getDraftMenuVersion() {
        return axiosInstance.get<MenuVersionManageDto>('menu-versions/draft');
    },

    async getArchivedMenuVersion(id: string) {
        return axiosInstance.get<MenuVersionManageDto>(`menu-versions/archived/${id}`);
    },

    async getArchivedMenuVersions() {
        return axiosInstance.get<MenuVersionArchivedListDto[]>('menu-versions/archived');
    },

    async publishMenuVersion() {
        return axiosInstance.post<MenuVersionManageDto>('menu-versions/publish');
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
    },

    async updateMenuEntry(id: string, sectionId: string, data: MenuEntryDto) {
        return axiosInstance.put<MenuEntryDto, MenuEntryDto>(`menu-versions/sections/${sectionId}/entries/${id}`, data);
    }
}