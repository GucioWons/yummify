import {DTOs} from "../../common/dtos.ts";
import DishListDTO = DTOs.DishListDTO;

export interface DishListElementProps {
    dish: DishListDTO
}

function DishListElement(props: DishListElementProps) {
    const {dish} = props;

    return (
        <div>
            {dish.name}
        </div>
    );
}

export default DishListElement;