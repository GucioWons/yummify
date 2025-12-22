import PageTitle from "../../common/PageTitle.tsx";
import {Plus} from "lucide-react";
import Button from "../../common/button/Button.tsx";
import IngredientsList from "./IngredientsList.tsx";
import {useState} from "react";
import IngredientFormModal from "../form/IngredientFormModal.tsx";

function IngredientListPage() {
    const [isFormModalOpen, setIsFormModalOpen] = useState(false);

    return (
        <>
        <PageTitle
            title='Ingredients'
            description='Track and manage ingredient stock levels'
            button={
                <Button
                    text='Add New Ingredient'
                    icon={Plus}
                    onClick={() => setIsFormModalOpen(prevState => !prevState)}
                />
            }
        />
            <IngredientsList/>
            {isFormModalOpen && (
                <IngredientFormModal onClose={() => setIsFormModalOpen(false)}/>
            )}
        </>
    );
}

export default IngredientListPage;