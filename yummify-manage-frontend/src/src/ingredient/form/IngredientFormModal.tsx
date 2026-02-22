import Modal from "../../common/modal/Modal.tsx";
import {Dtos} from "../../common/dtos.ts";
import IngredientForm from "./IngredientForm.tsx";
import IngredientManageDto = Dtos.IngredientManageDto;

export interface IngredientFormModalProps {
    ingredient?: IngredientManageDto;
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