import {useQuery} from "@tanstack/react-query";
import {Dtos} from "../../common/dtos.ts";
import DishManageDto = Dtos.DishManageDto;
import {dishService} from "../service/dishService.ts";
import TranslatedTextFieldDisplay from "../../common/display/TranslatedTextFieldDisplay.tsx";

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
            <label style={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'flex-start'
            }}>
                <span>Image Url</span>
                <span>{data?.imageUrl}</span>
            </label>
            <TranslatedTextFieldDisplay label='Name' value={data?.name}/>
            <TranslatedTextFieldDisplay label='Description' value={data?.description}/>
            <label style={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'flex-start'
            }}>
                <span>Ingredient</span>
                <span>{data?.ingredientIds.map((id) => id).join(',')}</span>
            </label>

        </div>
    );
}

export default DishDetail;