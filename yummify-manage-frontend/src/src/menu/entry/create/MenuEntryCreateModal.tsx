import Modal from "../../../common/modal/Modal.tsx";
import MenuEntryCreateForm from "./MenuEntryCreateForm.tsx";

export interface MenuEntryCreateModalProps {
    sectionId: string;
    onClose: () => void;
}

function MenuEntryCreateModal(props: MenuEntryCreateModalProps) {
    const {sectionId, onClose} = props;

    return (
        <Modal title={"Add New Entry"} onClose={onClose}>
            <MenuEntryCreateForm sectionId={sectionId} onCancel={onClose} />
        </Modal>
    );
}

export default MenuEntryCreateModal;