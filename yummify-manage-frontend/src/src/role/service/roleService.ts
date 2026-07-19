import axiosInstance from "../../common/api/axiosInstance.ts";
import {Dtos} from "../../common/dtos.ts";
import RoleListDto = Dtos.RoleListDto;
import RoleManageDto = Dtos.RoleManageDto;

export const roleService = {
    async createRole(data: RoleManageDto) {
        return axiosInstance.post<RoleManageDto>('roles', data);
    },

    async getRoles() {
        return axiosInstance.get<RoleListDto[]>('roles');
    },

    async getRole(id: string) {
        return axiosInstance.get<RoleManageDto>(`roles/${id}`);
    },
}