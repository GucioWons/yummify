import {Dtos} from "../../common/dtos.ts";
import MenuVersionManageDto = Dtos.MenuVersionManageDto;
import TranslatedTextFieldDisplay from "../../common/display/fields/TranslatedTextFieldDisplay.tsx";

export interface MenuVersionsTempProps {
    menuVersion: MenuVersionManageDto,
    title: string
}

function MenuVersionsTemp(props: MenuVersionsTempProps) {
    const {menuVersion, title} = props;

    return (
        <div style={{
            width: "50%",
        }}>
            <div>
                {title}
            </div>
            <div>
                {menuVersion.sections.length } sections • {menuVersion.sections.flatMap(section => section.entries).length} items
            </div>
            {menuVersion.sections.map((section) =>
                <div>
                    <TranslatedTextFieldDisplay label="Section" value={section.name}/>
                    {section.entries.map((entry) => <div>{entry.dishId} - {entry.price} $</div>)}
                </div>
            )}
        </div>
    );
}

export default MenuVersionsTemp;