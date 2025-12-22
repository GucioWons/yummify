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
        <Modal title="Add New Table" subtitle="Add a new table to your restaurant" onClose={onClose}>
            <IngredientForm ingredient={ingredient} onCancel={onClose} />
        </Modal>
    )
}

export default IngredientFormModal;