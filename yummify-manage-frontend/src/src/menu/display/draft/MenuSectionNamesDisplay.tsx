import {Dtos} from "../../../common/dtos.ts";
import MenuSectionManageDto = Dtos.MenuSectionManageDto;
import {useState} from "react";
import Display from "../../../common/display/Display.tsx";
import TranslatedTextFieldDisplay from "../../../common/display/fields/TranslatedTextFieldDisplay.tsx";
import Select from "react-select";

export interface MenuSectionNamesDisplayProps {
    sections: MenuSectionManageDto[];
    onEditClick: () => void;
    onCloseClick: () => void;
}

function MenuSectionNamesDisplay(props: MenuSectionNamesDisplayProps) {
    const {sections, onEditClick, onCloseClick} = props;

    const [selectedSectionId, setSelectedSectionId] = useState<string>(sections[0].id);

    const selectedSection = sections.find(s => s.id === selectedSectionId);

    return (
        <Display onEditClick={onEditClick} onCloseClick={onCloseClick}>
            <Select
                options={sections}
                value={selectedSection}
                onChange={(selected) => setSelectedSectionId(selected!.id)}
                placeholder="Select Section"
                menuPortalTarget={document.body}
                styles={{
                    menuPortal: (base) => ({
                        ...base,
                        zIndex: 9999
                    }),
                    menu: (base) => ({
                        ...base,
                        borderRadius: 8,             // zaokrąglone rogi
                        boxShadow: "0 4px 16px rgba(0,0,0,0.2)", // naturalny cień
                        overflow: "visible",         // żeby padding/margin opcji nie był uciety
                        padding: 4
                    }),
                    control: (base) => ({
                        ...base,
                        border: "2px solid #a7f3d0",
                        borderRadius: "8px",
                        boxShadow: "none",
                        fontSize: "14px",
                        fontFamily: "Inter, sans-serif"
                    }),
                    option: (base, state) => ({
                        ...base,
                        borderRadius: "8px",
                        padding: 4,
                        color: "black",
                        textAlign: "left",
                        backgroundColor: state.isFocused ? "#d1fae5" : "#ffffff",
                    }),
                    valueContainer: (base) => ({
                        ...base,
                        textAlign: "left",
                        flexWrap: "wrap",
                        padding: "8px",
                    }),
                    input: (base) => ({
                        ...base,
                        margin: 0,
                        padding: 0,
                        fontSize: "14px",
                    }),
                    indicatorsContainer: (base) => ({
                        ...base,
                        alignItems: "center",
                    })
                }}
                isSearchable={false}
                getOptionLabel={(option) => option.name.translations["EN"]}
            />

            {selectedSection &&
                <TranslatedTextFieldDisplay label="Section name" value={selectedSection.name} />
            }
        </Display>
    );
}

export default MenuSectionNamesDisplay;