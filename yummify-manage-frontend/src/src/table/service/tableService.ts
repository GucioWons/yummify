import axiosInstance from "../../common/api/axiosInstance.ts";
import {Dtos} from "../../common/dtos.ts";
import TableDto = Dtos.TableDto;

export const tableService = {
    async getTables() {
        return axiosInstance.get<TableDto[]>('tables');
    },

    async createTable(data: TableDto) {
        return axiosInstance.post<TableDto, TableDto>('tables', data);
    },
}