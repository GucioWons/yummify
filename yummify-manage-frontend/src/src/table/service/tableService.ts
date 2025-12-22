import axiosInstance from "../../common/api/axiosInstance.ts";
import {DTOs} from "../../common/dtos.ts";
import TableDTO = DTOs.TableDTO;

export const tableService = {
    async getTables() {
        return axiosInstance.get<TableDTO[]>('tables');
    },

    async createTable(data: TableDTO) {
        return axiosInstance.post<TableDTO, TableDTO>('tables', data);
    },
}