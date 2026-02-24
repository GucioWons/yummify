import {Dtos} from "../../common/dtos.ts";
import TranslatedTextFieldDisplay from "../../common/display/fields/TranslatedTextFieldDisplay.tsx";
import "./DishDisplay.css"
import DishIngredientsDisplay from "./DishIngredientsDisplay.tsx";
import ImageDisplay from "../../common/image/ImageDisplay.tsx";
import Display from "../../common/display/Display.tsx";
import DishManageDto = Dtos.DishManageDto;

export interface DishDisplayProps {
    dish: DishManageDto;
    image?: File;
    onEditClick: () => void;
}

function DishDisplay(props: DishDisplayProps) {
    const {dish, image, onEditClick} = props;

    return (
        <Display onEditClick={onEditClick} onCloseClick={() => {}}>
            {image && <ImageDisplay image={image}/>}
            <TranslatedTextFieldDisplay label='Name' value={dish.name}/>
            <TranslatedTextFieldDisplay label='Description' value={dish.description}/>
            <DishIngredientsDisplay ids={dish.ingredientIds} />
        </Display>
    );
}

export default DishDisplay;