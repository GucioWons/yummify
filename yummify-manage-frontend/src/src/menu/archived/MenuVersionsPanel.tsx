import {Dtos} from "../../common/dtos.ts";
import MenuVersionManageDto = Dtos.MenuVersionManageDto;
import TranslatedTextFieldDisplay from "../../common/display/fields/TranslatedTextFieldDisplay.tsx";
import Divider from "../../common/divider/Divider.tsx";
import FieldDisplay from "../../common/display/fields/FieldDisplay.tsx";

export interface MenuVersionsPanelProps {
    menuVersion: MenuVersionManageDto,
    title: string
}

function MenuVersionsPanel(props: MenuVersionsPanelProps) {
    const {menuVersion, title} = props;

    return (
        <div className="menu-versions-panel">
            <div>
                {title}
            </div>
            <div>
                {menuVersion.sections.length } sections • {menuVersion.sections.flatMap(section => section.entries).length} items
            </div>

            <Divider />

            <div className="menu-versions-panel-sections">
                {menuVersion.sections
                    .sort((a, b) => a.position - b.position)
                    .map((section) =>
                        <div>
                            <TranslatedTextFieldDisplay label="Section name:" value={section.name}/>
                            <FieldDisplay label="Dishes:">
                                {section.entries.map((entry) => <div>{entry.dishId} - {entry.price} $</div>)}
                            </FieldDisplay>
                            <Divider/>
                        </div>
                    )}
            </div>
        </div>
    );
}

export default MenuVersionsPanel;