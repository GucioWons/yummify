import Modal from "../../common/modal/Modal.tsx";
import RoleCreateForm from "./RoleCreateForm.tsx";

export interface RoleCreateModalProps {
    onClose: () => void;
}

function RoleCreateModal(props: RoleCreateModalProps) {
    const {onClose} = props;

    return (
        <Modal title="Add New Role" subtitle="Add a new role to your restaurant" onClose={onClose}>
            <RoleCreateForm onCancel={onClose} />
        </Modal>
    )
}

export default RoleCreateModal;