import {Dtos} from "../../../common/dtos.ts";
import Display from "../../../common/display/Display.tsx";
import TranslatedTextFieldDisplay from "../../../common/display/fields/TranslatedTextFieldDisplay.tsx";
import SelectInput from "../../../common/input/SelectInput.tsx";
import MenuSectionManageDto = Dtos.MenuSectionManageDto;

export interface MenuSectionNamesDisplayProps {
    sections: MenuSectionManageDto[];
    selectedSection?: MenuSectionManageDto;
    setSelectedSection: (section: MenuSectionManageDto) => void;
    onEditClick: () => void;
    onCloseClick: () => void;
}

function MenuSectionNamesDisplay(props: MenuSectionNamesDisplayProps) {
    const {sections, selectedSection, setSelectedSection, onEditClick, onCloseClick} = props;

    return (
        <Display onEditClick={onEditClick} onCloseClick={onCloseClick}>
            <SelectInput
                options={sections}
                selectedOption={selectedSection}
                onChange={setSelectedSection}
                getOptionLabel={(section) => section.name.translations["EN"]}
                placeholder={"Select Section"}
            />

            {selectedSection &&
                <TranslatedTextFieldDisplay label="Section name" value={selectedSection.name} />
            }
        </Display>
    );
}

export default MenuSectionNamesDisplay;