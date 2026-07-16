import Modal from "../../common/modal/Modal.tsx";
import UserCreateForm from "./UserCreateForm.tsx";

export interface UserCreateModalProps {
    onClose: () => void;
}

function UserCreateModal(props: UserCreateModalProps) {
    const {onClose} = props;

    return (
        <Modal title="Add New User" subtitle="Add a new user to your inventory" onClose={onClose}>
            <UserCreateForm onCancel={onClose} />
        </Modal>
    )
}

export default UserCreateModal;