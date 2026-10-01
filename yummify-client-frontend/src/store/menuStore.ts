import {OrderClientDTO} from "@/types/order";
import {create} from "zustand/index";
import {MenuVersionClientDTO} from "@/types/menu";

interface MenuState {
    menu: MenuVersionClientDTO | null;

    setMenu: (order: MenuVersionClientDTO) => void;
    clearMenu: () => void;
}

export const useMenuStore = create<MenuState>((set) => ({
    menu: null,

    setMenu: (menu) => set({ menu }),

    clearMenu: () => set({ menu: null }),
}));