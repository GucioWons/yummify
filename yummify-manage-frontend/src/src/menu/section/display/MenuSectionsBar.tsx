import {Dtos} from "../../../common/dtos.ts";
import MenuSectionManageDto = Dtos.MenuSectionManageDto;
import {Plus, Settings} from "lucide-react";
import Button from "../../../common/button/Button.tsx";

export interface MenuSectionsBarProps {
    sections: MenuSectionManageDto[];
    activeSectionId: string | null;
    setActiveSectionId: (sectionId: string) => void;
    isDraft?: boolean;
    onSectionNamesButtonClick?: () => void;
    onAddSectionButtonClick?: () => void;
}

function MenuSectionsBar(props: MenuSectionsBarProps) {
    const {
        isDraft,
        sections,
        activeSectionId,
        setActiveSectionId,
        onSectionNamesButtonClick,
        onAddSectionButtonClick
    } = props;

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
            {isDraft && onAddSectionButtonClick &&
                <Button text="Add Section" icon={Plus} onClick={onAddSectionButtonClick} />
            }
            {isDraft && onSectionNamesButtonClick &&
                <Button text="Section Names" icon={Settings} outlined onClick={onSectionNamesButtonClick} />
            }
        </div>
    )
}

export default MenuSectionsBar;