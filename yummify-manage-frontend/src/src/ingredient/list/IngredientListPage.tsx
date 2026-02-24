import PageTitle from "../../common/PageTitle.tsx";
import {Plus} from "lucide-react";
import Button from "../../common/button/Button.tsx";
import {useState} from "react";
import IngredientCreateModal from "../create/IngredientCreateModal.tsx";
import IngredientList from "./IngredientList.tsx";

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
            <IngredientList/>
            {isFormModalOpen && (
                <IngredientCreateModal onClose={() => setIsFormModalOpen(false)}/>
            )}
        </>
    );
}

export default IngredientListPage;