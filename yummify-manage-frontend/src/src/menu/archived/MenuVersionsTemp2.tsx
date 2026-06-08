import {Dtos} from "../../common/dtos.ts";
import MenuVersionManageDto = Dtos.MenuVersionManageDto;
import {useQuery} from "@tanstack/react-query";
import {menuService} from "../service/menuService.ts";
import MenuVersionsTemp from "./MenuVersionsTemp.tsx";
import MenuVersionArchivedListDto = Dtos.MenuVersionArchivedListDto;
import {formatDate} from "../../common/date/dateFormatter.ts";

export interface MenuVersionsTemp2Props {
    selectedArchivedVersion: MenuVersionArchivedListDto | null;
    publishedVersion: MenuVersionManageDto;
}

function MenuVersionsTemp2(props: MenuVersionsTemp2Props) {
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
            <MenuVersionsTemp menuVersion={publishedVersion} title="Published" />
        );
    }

    if (isArchivedLoading) return "Loading...";
    if (isArchivedError) return "Error...";

    console.log(archived);
    return (
        <MenuVersionsTemp
            menuVersion={archived!}
            title={`v${selectedArchivedVersion.version} - ${formatDate(selectedArchivedVersion.archivedAt)}`}
        />
    )
}

export default MenuVersionsTemp2;