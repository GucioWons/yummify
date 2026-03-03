import {Dtos} from "../../../../common/dtos.ts";
import MenuSectionManageDto = Dtos.MenuSectionManageDto;

export interface DraftMenuSectionBarProps {
    sections: MenuSectionManageDto[];
    activeSectionId: string | null;
    setActiveSectionId: (sectionId: string) => void;
}

function DraftMenuSectionBar(props: DraftMenuSectionBarProps) {
    const {sections, activeSectionId, setActiveSectionId} = props;

    return (
        <div className="menu-section-bar">
            {sections.map(section => {
                const active = section.id === activeSectionId;

                return (
                    <button
                        key={section.id}
                        onClick={() => setActiveSectionId(section.id)}
                        className={`menu-section-bar-item ${active ? "active" : ""}`}
                    >
                        {section.name.translations["PL"]}
                    </button>
                );
            })}
        </div>
    )
}

export default DraftMenuSectionBar;