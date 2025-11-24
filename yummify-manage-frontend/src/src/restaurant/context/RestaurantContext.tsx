import {createContext} from "react";
import {DTOs} from "../../common/dtos.ts";
import RestaurantClientDTO = DTOs.RestaurantClientDTO;

export interface RestaurantContextType {
    restaurant: RestaurantClientDTO | null;
    isLoading: boolean;
    refetch: () => void;
}

export const RestaurantContext = createContext<RestaurantContextType>({
    restaurant: null,
    isLoading: false,
    refetch: () => {},
});
