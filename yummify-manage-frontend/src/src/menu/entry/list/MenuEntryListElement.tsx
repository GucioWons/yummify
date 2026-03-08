import {Dtos} from "../../../common/dtos.ts";
import MenuEntryDto = Dtos.MenuEntryDto;
import {useQuery} from "@tanstack/react-query";
import {dishService} from "../../../dish/service/dishService.ts";
import DishManageDto = Dtos.DishManageDto;
import {formatCurrency} from "../../../common/useCurrencyFormatter.ts";
import LoadingSpinner from "../../../common/loading/LoadingSpinner.tsx";
import {Pen} from "lucide-react";

export interface MenuEntryListElementProps {
    entry: MenuEntryDto;
    isEditable?: boolean;
    onEditClick?: (entry: MenuEntryDto) => void;
}

function MenuEntryListElement(props: MenuEntryListElementProps) {
    const {entry, isEditable, onEditClick} = props;

    const {data: dish, isLoading: isDishLoading, isError: isDishError} = useQuery<DishManageDto>({
        queryKey: ["dishes", entry.dishId],
        queryFn: () => dishService.getDish(entry.dishId).then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    if (isDishLoading) return <LoadingSpinner height={40} width={40}/>;
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
            <div style={{
                display: "flex",
                alignItems: "center",
                gap: 8
            }}>
                {formatCurrency(entry.price, "EUR")}
                {isEditable && onEditClick &&
                    <button
                        onClick={() => onEditClick(entry)}
                        className="icon-button"
                        style={{
                            color: "#059669"
                        }}
                    >
                        <Pen width={20} height={20} />
                    </button>
                }
            </div>
        </div>
    );
}

export default MenuEntryListElement;