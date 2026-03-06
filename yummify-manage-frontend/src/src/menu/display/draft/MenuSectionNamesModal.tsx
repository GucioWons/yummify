import {Dtos} from "../../../common/dtos.ts";
import MenuSectionManageDto = Dtos.MenuSectionManageDto;
import Modal from "../../../common/modal/Modal.tsx";
import MenuSectionNamesDisplay from "./MenuSectionNamesDisplay.tsx";
import {useCallback, useState} from "react";
import MenuSectionUpdateForm from "./MenuSectionUpdateForm.tsx";

export interface MenuSectionNamesModalProps {
    sections: MenuSectionManageDto[];
    onClose: () => void;
}

function MenuSectionNamesModal(props: MenuSectionNamesModalProps) {
    const {sections, onClose} = props;

    const [isInEditState, setIsInEditState] = useState(false);

    const getTitle = useCallback(() => {
        return isInEditState ? "Edit Menu Section Name" : "Menu Section Names";
    }, [isInEditState]);

    const [selectedSection, setSelectedSection] = useState<MenuSectionManageDto>();

    return (
        <Modal title={getTitle()} onClose={onClose}>
            {isInEditState && selectedSection
                ? <MenuSectionUpdateForm section={selectedSection} onCancel={() => setIsInEditState(false)} />
                : <MenuSectionNamesDisplay
                    sections={sections}
                    selectedSection={selectedSection}
                    setSelectedSection={setSelectedSection}
                    onCloseClick={onClose}
                    onEditClick={() => setIsInEditState(true)}
                />
            }
        </Modal>
    )
}

export default MenuSectionNamesModal;