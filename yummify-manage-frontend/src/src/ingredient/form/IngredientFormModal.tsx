import Modal from "../../common/modal/Modal.tsx";
import {DTOs} from "../../common/dtos.ts";
import IngredientManageDTO = DTOs.IngredientManageDTO;
import IngredientForm from "./IngredientForm.tsx";

export interface IngredientFormModalProps {
    ingredient?: IngredientManageDTO;
    onClose: () => void;
}

function IngredientFormModal(props: IngredientFormModalProps) {
    const {ingredient, onClose} = props;

    return (
        <Modal title="Add New Ingredient" subtitle="Add a new ingredient to your inventory" onClose={onClose}>
            <IngredientForm ingredient={ingredient} onCancel={onClose} />
        </Modal>
    )
}

export default IngredientFormModal;