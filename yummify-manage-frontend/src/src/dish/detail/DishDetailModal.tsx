import Modal from "../../common/modal/Modal.tsx";
import DishDetail from "./DishDetail.tsx";

export interface DishDetailModalProps {
    id: string
    onClose: () => void;
}

function DishDetailModal(props: DishDetailModalProps) {
    const {id, onClose} = props;

    return (
        <Modal title="Dish Details" onClose={onClose}>
            <DishDetail id={id}/>
        </Modal>
    )
}

export default DishDetailModal;