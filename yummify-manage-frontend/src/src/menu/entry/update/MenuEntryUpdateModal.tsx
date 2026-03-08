import Modal from "../../../common/modal/Modal.tsx";
import {Dtos} from "../../../common/dtos.ts";
import MenuEntryUpdateForm from "./MenuEntryUpdateForm.tsx";
import MenuEntryDto = Dtos.MenuEntryDto;

export interface MenuEntryUpdateModalProps {
    entry: MenuEntryDto;
    sectionId: string;
    onClose: () => void;
}

function MenuEntryCreateModal(props: MenuEntryUpdateModalProps) {
    const {entry, sectionId, onClose} = props;

    return (
        <Modal title={"Edit Entry"} onClose={onClose}>
            <MenuEntryUpdateForm entry={entry} sectionId={sectionId} onCancel={onClose} />
        </Modal>
    );
}

export default MenuEntryCreateModal;