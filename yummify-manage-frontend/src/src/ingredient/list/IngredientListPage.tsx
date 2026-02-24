import PageTitle from "../../common/PageTitle.tsx";
import {Plus} from "lucide-react";
import Button from "../../common/button/Button.tsx";
import {useState} from "react";
import IngredientCreateModal from "../create/IngredientCreateModal.tsx";
import IngredientList from "./IngredientList.tsx";
import IngredientDisplayModal from "../display/IngredientDisplayModal.tsx";

function IngredientListPage() {
    const [isFormModalOpen, setIsFormModalOpen] = useState(false);

    const [selectedIngredientId, setSelectedIngredientId] = useState<string | null>();

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
            <IngredientList onElementClick={(ingredient) => setSelectedIngredientId(ingredient.id)}/>
            {isFormModalOpen && (
                <IngredientCreateModal onClose={() => setIsFormModalOpen(false)}/>
            )}
            {selectedIngredientId && (
                <IngredientDisplayModal id={selectedIngredientId} onClose={() => setSelectedIngredientId(null)}/>
            )}
        </>
    );
}

export default IngredientListPage;