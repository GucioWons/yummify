import FieldDisplay from "../../common/display/fields/FieldDisplay.tsx";

export interface DishIngredientsDisplayProps {
    ids?: string[];
}

function DishIngredientsDisplay(props: DishIngredientsDisplayProps) {
    const {ids} = props;

    if (!ids) return null;

    return (
        <FieldDisplay label='Ingredients'>
            <div className='dish-ingredients-display'>
                {ids.map((id) => <div className='dish-ingredient-display'>{id}</div>)}
            </div>
        </FieldDisplay>
    );
}

export default DishIngredientsDisplay;