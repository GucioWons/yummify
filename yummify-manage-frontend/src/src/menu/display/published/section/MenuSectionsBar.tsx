import {Dtos} from "../../../../common/dtos.ts";
import MenuSectionManageDto = Dtos.MenuSectionManageDto;

export interface MenuSectionsBarProps {
    sections: MenuSectionManageDto[];
    activeSectionId: string | null;
    setActiveSectionId: (sectionId: string) => void;
}

function MenuSectionsBar(props: MenuSectionsBarProps) {
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
                        {section.name.translations["EN"]}
                    </button>
                );
            })}
        </div>
    )
}

export default MenuSectionsBar;