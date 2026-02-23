import {Dtos} from "../../common/dtos.ts";
import DishListDto = Dtos.DishListDto;

export interface DishListElementProps {
    dish: DishListDto;
}

function DishListElement(props: DishListElementProps) {
    const {dish} = props;

    return (
        <div className="dish-list-element">
            <div className="dish-list-element-field">
                {dish.name}
            </div>
            <div className="dish-list-element-field">
                Available
            </div>
        </div>
    )
}

export default DishListElement;