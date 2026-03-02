import Modal from "../../common/modal/Modal.tsx";
import TableCreateForm from "./TableCreateForm.tsx";

export interface TableCreateModalProps {
    onClose: () => void;
}

function TableCreateModal(props: TableCreateModalProps) {
    const {onClose} = props;

    return (
        <Modal title="Add New Table" subtitle="Add a new table to your restaurant" onClose={onClose}>
            <TableCreateForm onCancel={onClose} />
        </Modal>
    )
}

export default TableCreateModal;