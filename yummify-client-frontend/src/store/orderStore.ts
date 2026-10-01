import { create } from "zustand";
import {OrderClientDTO, OrderItemClientDTO} from "@/types/order";

interface OrderState {
    order: OrderClientDTO | null;
    setOrder: (order: OrderClientDTO) => void;
    addOrUpdateItem: (item: OrderItemClientDTO) => void;
    removeItem: (itemId: string) => void;
    clearOrder: () => void;
}

export const useOrderStore = create<OrderState>((set) => ({
    order: null,

    setOrder: (order) => set({ order }),

    addOrUpdateItem: (item: OrderItemClientDTO) =>
        set(state => {
            if (!state.order) {
                return state;
            }

            const existingItemIndex = state.order.items.findIndex(
                existingItem => existingItem.id === item.id
            );

            if (existingItemIndex === -1) {
                return {
                    order: {
                        ...state.order,
                        items: [...state.order.items, item],
                    },
                };
            }

            const items = [...state.order.items];
            items[existingItemIndex] = item;

            return {
                order: {
                    ...state.order,
                    items,
                },
            };
        }),

    removeItem: (itemId: string) =>
        set(state => {
            if (!state.order) {
                return state;
            }

            return {
                order: {
                    ...state.order,
                    items: state.order.items.filter(
                        item => item.id !== itemId
                    ),
                },
            };
        }),

    clearOrder: () => set({ order: null }),
}));