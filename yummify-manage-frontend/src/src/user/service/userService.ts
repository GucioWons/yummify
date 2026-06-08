import axiosInstance from "../../common/api/axiosInstance.ts";
import {Dtos} from "../../common/dtos.ts";
import UserManageDto = Dtos.UserManageDto;

export const userService = {
    async getUsers() {
        return axiosInstance.get<UserManageDto[]>('users');
    },
}