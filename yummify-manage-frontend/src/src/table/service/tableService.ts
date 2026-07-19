import axiosInstance from "../../common/api/axiosInstance.ts";
import {Dtos} from "../../common/dtos.ts";
import TableDto = Dtos.TableDto;
import TableOtpDto = Dtos.TableOtpDto;

export const tableService = {
    async getTables() {
        return axiosInstance.get<TableDto[]>('tables');
    },

    async createTable(data: TableDto) {
        return axiosInstance.post<TableDto, TableDto>('tables', data);
    },

    async updateTable(data: TableDto) {
        return axiosInstance.put<TableDto, TableDto>(`tables/${data.id}`, data);
    },

    async generateOtp(id: string) {
        return axiosInstance.post<TableOtpDto>(`tables/${id}/generate-otp`);
    }
}