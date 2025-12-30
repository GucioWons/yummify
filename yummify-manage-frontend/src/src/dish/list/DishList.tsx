import {useQuery} from "@tanstack/react-query";
import {ingredientService} from "../../ingredient/service/ingredientService.ts";
import List from "../../common/list/List.tsx";
import IngredientListElement from "../../ingredient/list/IngredientListElement.tsx";
import {DTOs} from "../../common/dtos.ts";
import DishListDTO = DTOs.DishListDTO;
import {dishService} from "../service/dishService.ts";
import DishListElement from "./DishListElement.tsx";

function DishList() {
    const {data, isLoading, isError} = useQuery<DishListDTO[]>({
        queryKey: ["dishes"],
        queryFn: () => dishService.getDishes().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isLoading) return <div>Ładowanie...</div>;
    if (isError) return <div>Błąd podczas pobierania dań.</div>;

    return (
        <List
            items={data!}
            columns={2}
            renderItem={(dish) => <DishListElement dish={dish}/>}
        />
    );
}

export default DishList;