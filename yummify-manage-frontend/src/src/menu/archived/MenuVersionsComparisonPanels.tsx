import {Dtos} from "../../common/dtos.ts";
import MenuVersionManageDto = Dtos.MenuVersionManageDto;
import MenuVersionArchivedListDto = Dtos.MenuVersionArchivedListDto;
import MenuVersionsTemp2 from "./MenuVersionsTemp2.tsx";
import MenuVersionsPanel from "./MenuVersionsPanel.tsx";

export interface MenuVersionsComparisonPanelsProps {
    draft: MenuVersionManageDto;
    selectedArchived: MenuVersionArchivedListDto | null;
    published: MenuVersionManageDto;
}

function MenuVersionsComparisonPanels(props: MenuVersionsComparisonPanelsProps) {
    const {draft, selectedArchived, published} = props;

    return (
        <div className="menu-versions-comparison-panels">
            <MenuVersionsPanel menuVersion={draft!} title="Current"/>
            <MenuVersionsTemp2 selectedArchivedVersion={selectedArchived} publishedVersion={published!}/>
        </div>
    );

}

export default MenuVersionsComparisonPanels;