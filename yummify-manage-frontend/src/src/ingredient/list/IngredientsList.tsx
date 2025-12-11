import {useQuery} from "@tanstack/react-query";
import {DTOs} from "../../common/dtos.ts";
import IngredientListDTO = DTOs.IngredientListDTO;
import {ingredientService} from "../service/ingredientService.ts";
import "./IngredientList.css";
import IngredientListElement from "./IngredientListElement.tsx";


function IngredientsList() {
    const {data, isLoading, isError} = useQuery<IngredientListDTO[]>({
        queryKey: ["ingredients"],
        queryFn: () => ingredientService.getIngredients().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isLoading) return <div>Ładowanie...</div>;
    if (isError) return <div>Błąd podczas pobierania składników.</div>;

    return (
        <div>
            {data?.map(
                ingredient => <IngredientListElement key={ingredient.id} ingredient={ingredient}/>
            )
            }
        </div>
    )
}

export default IngredientsList;