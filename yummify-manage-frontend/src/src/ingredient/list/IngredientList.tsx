import {useQuery} from "@tanstack/react-query";
import {Dtos} from "../../common/dtos.ts";
import {ingredientService} from "../service/ingredientService.ts";
import "./IngredientList.css";
import IngredientListElement from "./IngredientListElement.tsx";
import List from "../../common/list/List.tsx";
import IngredientClientDto = Dtos.IngredientClientDto;

export interface IngredientListProps {
    onElementClick: (dish: IngredientClientDto) => void;
}

function IngredientList(props: IngredientListProps) {
    const {onElementClick} = props;

    const {data, isLoading, isError} = useQuery<IngredientClientDto[]>({
        queryKey: ["ingredients"],
        queryFn: () => ingredientService.getIngredients().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isLoading) return <div>Ładowanie...</div>;
    if (isError) return <div>Błąd podczas pobierania składników.</div>;

    return (
        <List
            items={data!}
            onItemClick={onElementClick}
            renderItem={(ingredient) => <IngredientListElement ingredient={ingredient}/>}
        />
    );
}

export default IngredientList;