import {useQuery} from "@tanstack/react-query";
import List from "../../common/list/List.tsx";
import {Dtos} from "../../common/dtos.ts";
import {dishService} from "../service/dishService.ts";
import DishListDto = Dtos.DishListDto;
import DishListElement from "./DishListElement.tsx";
import "./DishList.css";

function DishesList() {
    const {data, isLoading, isError} = useQuery<DishListDto[]>({
        queryKey: ["dishes"],
        queryFn: () => dishService.getDishes().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isLoading) return <div>Ładowanie...</div>;
    if (isError) return <div>Błąd podczas pobierania dań.</div>;

    return (
        <List
            items={data!}
            columns={1}
            renderItem={(dish) => <DishListElement dish={dish}/>}
        />
    );
}

export default DishesList;