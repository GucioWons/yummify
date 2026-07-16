import {Dtos} from "../../common/dtos.ts";
import UserManageDto = Dtos.UserManageDto;
import Modal from "../../common/modal/Modal.tsx";
import UserDisplay from "./UserDisplay.tsx";

export interface UserDisplayModalProps {
    user: UserManageDto
    onClose: () => void;
}

function UserDisplayModal(props: UserDisplayModalProps) {
    const {user, onClose} = props;

    return (
        <Modal title="User details" onClose={onClose}>
                <UserDisplay
                    user={user}
                    onCloseClick={onClose}
                />
        </Modal>
    );
}

export default UserDisplayModal;