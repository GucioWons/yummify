import axiosInstance from "../../common/api/axiosInstance.ts";
import {Dtos} from "../../common/dtos.ts";
import OrderClientDto = Dtos.OrderClientDto;

export const orderService = {
    async getCurrent() {
        return axiosInstance.get<OrderClientDto[]>('orders/current');
    },

    async getOld() {
        return axiosInstance.get<OrderClientDto[]>(`orders/old`);
    },
}