import {Dtos} from "../../common/dtos.ts";
import MenuVersionManageDto = Dtos.MenuVersionManageDto;
import TranslatedTextFieldDisplay from "../../common/display/fields/TranslatedTextFieldDisplay.tsx";
import Divider from "../../common/divider/Divider.tsx";

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
                {menuVersion.sections.map((section) =>
                    <div>
                        <TranslatedTextFieldDisplay label="Section" value={section.name}/>
                        {section.entries.map((entry) => <div>{entry.dishId} - {entry.price} $</div>)}
                    </div>
                )}
            </div>
        </div>
    );
}

export default MenuVersionsPanel;