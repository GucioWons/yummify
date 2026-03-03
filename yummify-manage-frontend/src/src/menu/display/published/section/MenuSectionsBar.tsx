import {Dtos} from "../../../../common/dtos.ts";
import MenuSectionClientDto = Dtos.MenuSectionClientDto;

export interface MenuSectionsBarProps {
    sections: MenuSectionClientDto[];
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
                        {section.name}
                    </button>
                );
            })}
        </div>
    )
}

export default MenuSectionsBar;