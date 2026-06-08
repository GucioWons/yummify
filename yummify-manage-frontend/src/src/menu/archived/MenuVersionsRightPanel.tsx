import {Dtos} from "../../common/dtos.ts";
import {useQuery} from "@tanstack/react-query";
import {menuService} from "../service/menuService.ts";
import MenuVersionsPanel from "./MenuVersionsPanel.tsx";
import {formatDate} from "../../common/date/dateFormatter.ts";
import MenuVersionManageDto = Dtos.MenuVersionManageDto;
import MenuVersionArchivedListDto = Dtos.MenuVersionArchivedListDto;

export interface MenuVersionsRightPanelProps {
    selectedArchivedVersion: MenuVersionArchivedListDto | null;
    publishedVersion: MenuVersionManageDto;
}

function MenuVersionsRightPanel(props: MenuVersionsRightPanelProps) {
    const {selectedArchivedVersion, publishedVersion} = props;

    const {data: archived, isLoading: isArchivedLoading, isError: isArchivedError} = useQuery<MenuVersionManageDto>({
        queryKey: ["menu-versions", "archived", selectedArchivedVersion?.id],
        queryFn: () => menuService
            .getArchivedMenuVersion(selectedArchivedVersion!.id)
            .then(res => res.data),
        enabled: !!selectedArchivedVersion,
        staleTime: 1000 * 60 * 5,
    });

    if (!selectedArchivedVersion) {
        return (
            <MenuVersionsPanel menuVersion={publishedVersion} title="Published" />
        );
    }

    if (isArchivedLoading) return "Loading...";
    if (isArchivedError) return "Error...";

    return (
        <MenuVersionsPanel
            menuVersion={archived!}
            title={`v${selectedArchivedVersion.version} - ${formatDate(selectedArchivedVersion.archivedAt)}`}
        />
    )
}

export default MenuVersionsRightPanel;