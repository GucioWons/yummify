import {useQuery} from "@tanstack/react-query";
import {Dtos} from "../../common/dtos.ts";
import DishManageDto = Dtos.DishManageDto;
import {dishService} from "../service/dishService.ts";

export interface DishDetailProps {
    id: string;
}

function DishDetail(props: DishDetailProps) {
    const {id} = props;

    const {data, isLoading, isError} = useQuery<DishManageDto>({
        queryKey: ["dishes", id],
        queryFn: () => dishService.getDish(id).then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isLoading) return <div>Ładowanie...</div>;
    if (isError) return <div>Błąd podczas pobierania dania.</div>;

    return (
        <div>
            {data?.imageUrl}
            {data && Object.entries(data.name.translations).map(([lang, text]) => (
            <div key={lang}>
                {lang}: {text}
            </div>
            ))}
            {data && Object.entries(data.description.translations).map(([lang, text]) => (
                <div key={lang}>
                    {lang}: {text}
                </div>
            ))}
            {data?.ingredientIds.map((id) => id).join(',')}
        </div>
    );
}

export default DishDetail;