import {DTOs} from "../../common/dtos.ts";
import IngredientClientDTO = DTOs.IngredientClientDTO;

export interface IngredientListElementProps {
    ingredient: IngredientClientDTO;
}

function IngredientListElement(props: IngredientListElementProps) {
    const {ingredient} = props;

    return (
        <div className="ingredient-list-element">
            <div className="ingredient-list-element-field">
                {ingredient.name}
            </div>
            <div style={{display: 'flex'}}>
                <div className="ingredient-list-element-field">
                    Stock: 50kg
                </div>
                <div className="ingredient-list-element-field">
                    Reorder at: 20kg
                </div>
            </div>
        </div>
    )
}

export default IngredientListElement;