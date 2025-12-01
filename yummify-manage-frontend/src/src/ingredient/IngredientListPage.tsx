import PageTitle from "../common/PageTitle.tsx";
import {Plus} from "lucide-react";
import Button from "../common/button/Button.tsx";

function IngredientListPage() {
    return (
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
    );
}

export default IngredientListPage;