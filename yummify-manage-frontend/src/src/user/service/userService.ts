import axiosInstance from "../../common/api/axiosInstance.ts";
import {Dtos} from "../../common/dtos.ts";
import UserManageDto = Dtos.UserManageDto;

export const userService = {
    async createUser(data: UserManageDto) {
        return axiosInstance.post<UserManageDto>('users', data);
    },

    async getUsers() {
        return axiosInstance.get<UserManageDto[]>('users');
    },
}