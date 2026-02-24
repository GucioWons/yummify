import {Dtos} from "../../common/dtos.ts";
import Display from "../../common/display/Display.tsx";
import TranslatedTextFieldDisplay from "../../common/display/fields/TranslatedTextFieldDisplay.tsx";
import IngredientManageDto = Dtos.IngredientManageDto;

export interface IngredientDisplayProps {
    ingredient: IngredientManageDto;
    onEditClick: () => void;
    onCloseClick: () => void;
}

function IngredientDisplay(props: IngredientDisplayProps) {
    const {ingredient, onEditClick, onCloseClick} = props;

    return (
        <Display onEditClick={onEditClick} onCloseClick={onCloseClick}>
            <TranslatedTextFieldDisplay label='Name' value={ingredient.name}/>
        </Display>
    );
}

export default IngredientDisplay;