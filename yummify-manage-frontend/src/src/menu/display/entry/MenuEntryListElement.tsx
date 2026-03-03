import {Dtos} from "../../../common/dtos.ts";
import MenuEntryDto = Dtos.MenuEntryDto;
import {useQuery} from "@tanstack/react-query";
import {dishService} from "../../../dish/service/dishService.ts";
import DishManageDto = Dtos.DishManageDto;
import {formatCurrency} from "../../../common/useCurrencyFormatter.ts";
import "./MenuEntryList.css"

export interface MenuEntryListElementProps {
    entry: MenuEntryDto;
}

function MenuEntryListElement(props: MenuEntryListElementProps) {
    const {entry} = props;

    const {data: dish, isLoading: isDishLoading, isError: isDishError} = useQuery<DishManageDto>({
        queryKey: ["dishes", entry.dishId],
        queryFn: () => dishService.getDish(entry.dishId).then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isDishLoading) return <div>Ładowanie...</div>;
    if (isDishError) return <div>Błąd podczas pobierania dania.</div>;

    return (
        <div className="menu-entry-list-element">
            <div>
                <div className="menu-entry-list-element-dish-name" >
                    {dish!.name.translations["EN"]}
                </div>
                <div className="menu-entry-list-element-dish-description">
                    {dish!.description.translations["EN"]}
                </div>
            </div>
            <div>
                {formatCurrency(entry.price, "EUR")}
            </div>
        </div>
    );
}

export default MenuEntryListElement;