import {useQuery} from "@tanstack/react-query";
import {DTOs} from "../../common/dtos.ts";
import IngredientListDTO = DTOs.IngredientListDTO;
import {ingredientService} from "../service/ingredientService.ts";
import "./IngredientList.css";
import IngredientListElement from "./IngredientListElement.tsx";
import List from "../../common/list/List.tsx";


function IngredientsList() {
    const {data, isLoading, isError} = useQuery<IngredientListDTO[]>({
        queryKey: ["ingredients"],
        queryFn: () => ingredientService.getIngredients().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isLoading) return <div>Ładowanie...</div>;
    if (isError) return <div>Błąd podczas pobierania składników.</div>;

    return (
        <List
            items={data!}
            columns={1}
            renderItem={(ingredient) => <IngredientListElement ingredient={ingredient}/>}
        />
    );
}

export default IngredientsList;