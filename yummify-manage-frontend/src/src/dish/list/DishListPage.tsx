import PageTitle from "../../common/PageTitle.tsx";
import {Plus} from "lucide-react";
import Button from "../../common/button/Button.tsx";
import {useState} from "react";
import DishFormModal from "../form/DishFormModal.tsx";
import DishesList from "./DishesList.tsx";

function DishListPage() {
    const [isFormModalOpen, setIsFormModalOpen] = useState(false);

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
            <DishesList/>
            {isFormModalOpen && (
                <DishFormModal onClose={() => setIsFormModalOpen(false)}/>
            )}
        </>
    );
}

export default DishListPage;