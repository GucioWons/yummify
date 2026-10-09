import axiosInstance from "../../common/api/axiosInstance.ts";
import {Dtos} from "../../common/dtos.ts";
import OrderDto = Dtos.OrderDto;
import OrderItemDto = Dtos.OrderItemDto;

export const orderService = {
    async getCurrent() {
        return axiosInstance.get<OrderDto[]>('orders/current');
    },

    async getOld() {
        return axiosInstance.get<OrderDto[]>(`orders/old`);
    },

    async startPreparation(id: string, itemId: string) {
        return axiosInstance.post<OrderItemDto>(`orders/${id}/items/${itemId}/start`);
    },

    async finishPreparation(id: string, itemId: string) {
        return axiosInstance.post<OrderItemDto>(`orders/${id}/items/${itemId}/finish`);
    },

    async serve(id: string, itemId: string) {
        return axiosInstance.post<OrderItemDto>(`orders/${id}/items/${itemId}/serve`);
    },
}