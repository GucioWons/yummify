import {RestaurantClientDTO} from "@/store/authStore";
import {apiClient} from "@/api/apiClient";
import {AddOrderItemDTO, OrderClientDTO, OrderItemClientDTO} from "@/types/order";

export const createOrder = async (): Promise<OrderClientDTO> => {
    const response = await apiClient.post<OrderClientDTO>("/client/orders");

    return response.data;
};

export const getOrder = async (): Promise<OrderClientDTO> => {
    const response = await apiClient.get<OrderClientDTO>("/client/orders");

    return response.data;
};

export const addItem = async (dto: AddOrderItemDTO): Promise<OrderItemClientDTO> => {
    const response = await apiClient.post<OrderItemClientDTO>("/client/orders/items", dto);

    return response.data;
}

export const removeItem = async (itemId: string) => {
    await apiClient.delete(`/client/orders/items/${itemId}`);
}

export const submitOrder = async () => {
    const response = await apiClient.post<OrderClientDTO>("/client/orders/submit");

    return response.data;
}

export const requestAssistance = async () => {
    const response = await apiClient.patch<OrderClientDTO>("/client/orders/assistance");

    return response.data;
}

export const requestPayment = async (): Promise<OrderClientDTO> => {
    const response = await apiClient.patch<OrderClientDTO>("/client/orders/payment");

    return response.data;
}