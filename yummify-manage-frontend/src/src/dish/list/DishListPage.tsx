import PageTitle from "../../common/PageTitle.tsx";
import {Plus} from "lucide-react";
import Button from "../../common/button/Button.tsx";
import {useState} from "react";
import DishCreateModal from "../create/DishCreateModal.tsx";
import DishList from "./DishList.tsx";
import DishDisplayModal from "../display/DishDisplayModal.tsx";

function DishListPage() {
    const [isFormModalOpen, setIsFormModalOpen] = useState(false);

    const [selectedDishId, setSelectedDishId] = useState<string | null>();

    return (
        <>
            <PageTitle
                title="Dishes"
                description="Manage your restaurant's dishes and recipes"
                button={
                    <Button
                        text='Add New Dish'
                        icon={Plus}
                        onClick={() => setIsFormModalOpen(prevState => !prevState)}
                    />
                }
            />
            <DishList onElementClick={(dish) => setSelectedDishId(dish.id)}/>
            {isFormModalOpen && (
                <DishCreateModal onClose={() => setIsFormModalOpen(false)}/>
            )}
            {selectedDishId && (
                <DishDisplayModal id={selectedDishId} onClose={() => setSelectedDishId(null)}/>
            )}
        </>
    );
}

export default DishListPage;