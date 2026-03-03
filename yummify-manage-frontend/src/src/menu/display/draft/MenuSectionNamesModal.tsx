import {Dtos} from "../../../common/dtos.ts";
import MenuSectionManageDto = Dtos.MenuSectionManageDto;
import Modal from "../../../common/modal/Modal.tsx";
import MenuSectionNamesDisplay from "./MenuSectionNamesDisplay.tsx";

export interface MenuSectionNamesModalProps {
    sections: MenuSectionManageDto[];
    onClose: () => void;
}

function MenuSectionNamesModal(props: MenuSectionNamesModalProps) {
    const {sections, onClose} = props;

    return (
        <Modal title="Menu Section Names" onClose={onClose}>
            <MenuSectionNamesDisplay sections={sections} onCloseClick={onClose} onEditClick={() => {}}/>
        </Modal>
    )
}

export default MenuSectionNamesModal;