import {DTOs} from "../../common/dtos.ts";
import DishManageDTO = DTOs.DishManageDTO;
import Modal from "../../common/modal/Modal.tsx";
import DishForm from "./DishForm.tsx";

export interface DishFormModalProps {
    dish?: DishManageDTO
    onClose: () => void;
}

function DishFormModal(props: DishFormModalProps) {
    const {dish, onClose} = props;

    return (
        <Modal title="Add New Dish" subtitle="Add a new dish to your restaurant" onClose={onClose}>
            <DishForm dish={dish} onCancel={onClose} />
        </Modal>
    )
}

export default DishFormModal;