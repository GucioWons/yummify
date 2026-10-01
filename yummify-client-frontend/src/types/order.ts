export interface OrderClientDTO {
    id: string;
    tableId: string;
    items: OrderItemClientDTO[];
    status: OrderStatus;
    assistanceRequested: boolean;
    paymentRequested: boolean;
}

export enum OrderStatus {
    NEW = "NEW",
    SUBMITTED = "SUBMITTED",
    IN_PREPARATION = "IN_PREPARATION",
    DELIVERED = "DELIVERED",
    COMPLETED = "COMPLETED",
    CANCELLED = "CANCELLED",
}

export interface OrderItemClientDTO {
    id: string;
    dishId: string;
    name: string;
    price: number;
    quantity: number;
    status: OrderItemStatus;
}

export enum OrderItemStatus {
    NEW = "NEW",
    IN_PREPARATION = "IN_PREPARATION",
    READY = "READY",
    DELIVERED = "DELIVERED",
    CANCELLED = "CANCELLED",
}

export interface AddOrderItemDTO {
    dishId: string;
    quantity: number;
}