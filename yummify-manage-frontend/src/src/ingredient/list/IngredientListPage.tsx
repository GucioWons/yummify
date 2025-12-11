import PageTitle from "../../common/PageTitle.tsx";
import {Plus} from "lucide-react";
import Button from "../../common/button/Button.tsx";
import IngredientsList from "./IngredientsList.tsx";

function IngredientListPage() {
    return (
        <>
        <PageTitle
            title='Ingredients'
            description='Track and manage ingredient stock levels'
            button={
                <Button
                    text='Add New Ingredient'
                    icon={Plus}
                />
            }
        />
            <IngredientsList/>
        </>
    );
}

export default IngredientListPage;