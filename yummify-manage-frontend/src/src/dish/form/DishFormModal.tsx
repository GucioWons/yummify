import Modal from "../../common/modal/Modal.tsx";
import DishCreateForm from "./DishCreateForm.tsx";

export interface DishFormModalProps {
    onClose: () => void;
}

function DishFormModal(props: DishFormModalProps) {
    const {onClose} = props;

    return (
        <Modal title="Add New Dish" subtitle="Add a new dish to your restaurant" onClose={onClose}>
            <DishCreateForm onCancel={onClose} />
        </Modal>
    )
}

export default DishFormModal;