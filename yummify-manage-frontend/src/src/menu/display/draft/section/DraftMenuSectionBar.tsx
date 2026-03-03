import {Dtos} from "../../../../common/dtos.ts";
import MenuSectionManageDto = Dtos.MenuSectionManageDto;
import Button from "../../../../common/button/Button.tsx";

export interface DraftMenuSectionBarProps {
    sections: MenuSectionManageDto[];
    activeSectionId: string | null;
    setActiveSectionId: (sectionId: string) => void;
    onSectionNamesButtonClick: () => void;
}

function DraftMenuSectionBar(props: DraftMenuSectionBarProps) {
    const {sections, activeSectionId, setActiveSectionId, onSectionNamesButtonClick} = props;

    return (
        <div style={{display: "flex", alignItems: "center", gap: 8, marginBottom: 12}}>
            <div className="menu-section-bar">
                {sections.map(section => {
                    const active = section.id === activeSectionId;

                    return (
                        <button
                            key={section.id}
                            onClick={() => setActiveSectionId(section.id)}
                            className={`menu-section-bar-item ${active ? "active" : ""}`}
                        >
                            {section.name.translations["EN"]}
                        </button>
                    );
                })}
            </div>
            <Button text="Section Names" outlined onClick={onSectionNamesButtonClick} />
        </div>
    )
}

export default DraftMenuSectionBar;