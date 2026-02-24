import Modal from "../../common/modal/Modal.tsx";
import IngredientCreateForm from "./IngredientCreateForm.tsx";

export interface IngredientCreateModalProps {
    onClose: () => void;
}

function IngredientCreateModal(props: IngredientCreateModalProps) {
    const {onClose} = props;

    return (
        <Modal title="Add New Ingredient" subtitle="Add a new ingredient to your inventory" onClose={onClose}>
            <IngredientCreateForm onCancel={onClose} />
        </Modal>
    )
}

export default IngredientCreateModal;