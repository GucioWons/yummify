import axiosInstance from "../../common/api/axiosInstance.ts";
import {Dtos} from "../../common/dtos.ts";
import RoleListDto = Dtos.RoleListDto;

export const roleService = {
    async getRoles() {
        return axiosInstance.get<RoleListDto[]>('roles');
    },
}