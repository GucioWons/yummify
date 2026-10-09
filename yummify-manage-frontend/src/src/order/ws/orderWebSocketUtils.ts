import {Dtos} from "../../common/dtos.ts";
import {QueryClient} from "@tanstack/react-query";
import OrderDto = Dtos.OrderDto;
import OrderStatus = Dtos.OrderStatus;
import OrderWebSocketMessage = Dtos.OrderWebSocketMessage;
import Type = Dtos.Type;

const CURRENT_ORDERS_KEY = ['orders', 'current'];
const OLD_ORDERS_KEY = ['orders', 'old'];

export function handleOrderMessage(
    event: OrderWebSocketMessage,
    queryClient: QueryClient,
): void {
    switch (event.type) {
        case Type.CREATED:
            addOrder(event.order, queryClient);
            break;
        case Type.UPDATED:
            updateOrder(event.order, queryClient);
            break;
    }
}

function addOrder(order: OrderDto, queryClient: QueryClient): void {
    if (isCurrentOrder(order)) {
        addToList(order, CURRENT_ORDERS_KEY, queryClient);
    } else {
        addToList(order, OLD_ORDERS_KEY, queryClient);
    }
}

function updateOrder(order: OrderDto, queryClient: QueryClient): void {
    const targetKey = isCurrentOrder(order) ? CURRENT_ORDERS_KEY : OLD_ORDERS_KEY;
    const sourceKey = isCurrentOrder(order) ? OLD_ORDERS_KEY : CURRENT_ORDERS_KEY;

    removeFromList(order.id, sourceKey, queryClient);
    upsertOrder(order, targetKey, queryClient);
}

function addToList(
    order: OrderDto,
    queryKey: readonly string[],
    queryClient: QueryClient,
): void {
    queryClient.setQueryData<OrderDto[]>(queryKey, orders => {
        if (!orders || orders.some(item => item.id === order.id)) {
            return orders;
        }

        return [order, ...orders];
    });
}

function upsertOrder(
    updatedOrder: OrderDto,
    queryKey: readonly string[],
    queryClient: QueryClient,
): void {
    queryClient.setQueryData<OrderDto[]>(queryKey, orders => {
        if (!orders) return orders;

        const existingOrder = orders.find(
            order => order.id === updatedOrder.id,
        );

        if (!existingOrder) {
            return [updatedOrder, ...orders];
        }

        if (areOrdersEqual(existingOrder, updatedOrder)) {
            return orders;
        }

        return orders.map(order =>
            order.id === updatedOrder.id ? updatedOrder : order,
        );
    });
}

function removeFromList(
    orderId: OrderDto['id'],
    queryKey: readonly string[],
    queryClient: QueryClient,
): void {
    queryClient.setQueryData<OrderDto[]>(queryKey, orders => {
        if (!orders || !orders.some(order => order.id === orderId)) {
            return orders;
        }

        return orders.filter(order => order.id !== orderId);
    });
}

function isCurrentOrder(order: OrderDto): boolean {
    return ![OrderStatus.COMPLETED, OrderStatus.CANCELLED,].includes(order.status);
}

function areOrdersEqual(a: OrderDto, b: OrderDto): boolean {
    return JSON.stringify(a) === JSON.stringify(b);
}