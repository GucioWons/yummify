import {Dtos} from "../../common/dtos.ts";
import DishManageDto = Dtos.DishManageDto;
import TranslatedTextFieldDisplay from "../../common/display/TranslatedTextFieldDisplay.tsx";
import "./DishDetail.css"
import DishIngredientsDisplay from "./DishIngredientsDisplay.tsx";
import Button from "../../common/button/Button.tsx";
import ImageDisplay from "../../common/image/ImageDisplay.tsx";

export interface DishDetailProps {
    dish: DishManageDto;
    image?: File;
    onEditClick: () => void;
}

function DishDetail(props: DishDetailProps) {
    const {dish, image, onEditClick} = props;

    return (
        <div>
            {image && <ImageDisplay image={image}/>}
            <TranslatedTextFieldDisplay label='Name' value={dish.name}/>
            <TranslatedTextFieldDisplay label='Description' value={dish.description}/>
            <DishIngredientsDisplay ids={dish.ingredientIds} />
            <div style={{
                display: 'flex',
                justifyContent: 'flex-end'
            }}>
                <Button text="Edit" onClick={onEditClick} type="button"/>
            </div>
        </div>
    );
}

export default DishDetail;