import React, {useContext} from "react";
import {AuthContext} from "../../auth/context/AuthContext.tsx";
import {useQuery} from "@tanstack/react-query";
import {restaurantService} from "../service/restaurantService.ts";
import {RestaurantContext} from "./RestaurantContext.tsx";
import {DTOs} from "../../common/dtos.ts";
import RestaurantClientDTO = DTOs.RestaurantClientDTO;

export interface RestaurantProviderProps {
    children: React.ReactNode;
}

function RestaurantProvider(props: RestaurantProviderProps) {
    const {children} = props;

    const { user } = useContext(AuthContext);

    const {
        data,
        isLoading,
        refetch,
    } = useQuery<RestaurantClientDTO>({
        queryKey: ["restaurants"],
        queryFn: () => restaurantService.getRestaurantClient().then(res => res.data),
        enabled: !!user,
        staleTime: 1000 * 60 * 5,
    });

    return (
        <RestaurantContext.Provider value={{ restaurant: data ?? null, isLoading, refetch }}>
            {children}
        </RestaurantContext.Provider>
    );
}

export default RestaurantProvider;