import { create } from "zustand";
import { DishClientDTO } from "@/types/dish";

interface DishesState {
    dishes: Record<string, DishClientDTO>;

    setDishes: (dishes: DishClientDTO[]) => void;
    clearDishes: () => void;
}

export const useDishesStore = create<DishesState>((set) => ({
    dishes: {},

    setDishes: (dishes) =>
        set({
            dishes: dishes.reduce<Record<string, DishClientDTO>>(
                (acc, dish) => {
                    acc[dish.id] = dish;
                    return acc;
                },
                {},
            ),
        }),

    clearDishes: () => set({ dishes: {} }),
}));