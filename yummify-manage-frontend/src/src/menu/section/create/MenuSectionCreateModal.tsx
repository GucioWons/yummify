import MenuSectionCreateForm from "./MenuSectionCreateForm.tsx";
import Modal from "../../../common/modal/Modal.tsx";

export interface MenuSectionCreateModalProps {
    onClose: () => void;
}

function MenuSectionCreateModal(props: MenuSectionCreateModalProps) {
    const {onClose} = props;

    return (
        <Modal title="Add Menu Section" onClose={onClose}>
            <MenuSectionCreateForm onCancel={onClose}/>
        </Modal>
    );
}

export default MenuSectionCreateModal;