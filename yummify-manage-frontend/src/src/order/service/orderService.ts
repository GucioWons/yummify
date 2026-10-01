import axiosInstance from "../../common/api/axiosInstance.ts";
import {Dtos} from "../../common/dtos.ts";
import OrderClientDto = Dtos.OrderClientDto;
import OrderItemClientDto = Dtos.OrderItemClientDto;

export const orderService = {
    async getCurrent() {
        return axiosInstance.get<OrderClientDto[]>('orders/current');
    },

    async getOld() {
        return axiosInstance.get<OrderClientDto[]>(`orders/old`);
    },

    async startPreparation(id: string, itemId: string) {
        return axiosInstance.post<OrderItemClientDto>(`orders/${id}/items/${itemId}/start`);
    },

    async finishPreparation(id: string, itemId: string) {
        return axiosInstance.post<OrderItemClientDto>(`orders/${id}/items/${itemId}/finish`);
    },

    async serve(id: string, itemId: string) {
        return axiosInstance.post<OrderItemClientDto>(`orders/${id}/items/${itemId}/serve`);
    },
}