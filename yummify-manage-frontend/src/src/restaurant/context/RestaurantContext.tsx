import {createContext} from "react";
import {Dtos} from "../../common/dtos.ts";
import RestaurantClientDto = Dtos.RestaurantClientDto;

export interface RestaurantContextType {
    restaurant: RestaurantClientDto | null;
    isLoading: boolean;
    refetch: () => void;
}

export const RestaurantContext = createContext<RestaurantContextType>({
    restaurant: null,
    isLoading: false,
    refetch: () => {},
});
