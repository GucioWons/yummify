import Modal from "../../common/modal/Modal.tsx";
import DishCreateForm from "./DishCreateForm.tsx";

export interface DishCreateModalProps {
    onClose: () => void;
}

function DishCreateModal(props: DishCreateModalProps) {
    const {onClose} = props;

    return (
        <Modal title="Add New Dish" subtitle="Add a new dish to your restaurant" onClose={onClose}>
            <DishCreateForm onCancel={onClose} />
        </Modal>
    )
}

export default DishCreateModal;