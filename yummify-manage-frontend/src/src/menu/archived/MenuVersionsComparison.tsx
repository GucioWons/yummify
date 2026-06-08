import {Dtos} from "../../common/dtos.ts";
import MenuVersionManageDto = Dtos.MenuVersionManageDto;
import {useQuery} from "@tanstack/react-query";
import {menuService} from "../service/menuService.ts";
import LoadingSpinner from "../../common/loading/LoadingSpinner.tsx";
import MenuVersionArchivedListDto = Dtos.MenuVersionArchivedListDto;
import MenuArchivedVersionsBar from "./MenuVersionsBar.tsx";
import {useState} from "react";
import Divider from "../../common/divider/Divider.tsx";
import MenuVersionsComparisonPanels from "./MenuVersionsComparisonPanels.tsx";
import MenuVersionsComparisonButtons from "./MenuVersionsComparisonButtons.tsx";

export interface MenuVersionsComparisonProps {
    onClose: () => void;
}

function MenuVersionsComparison(props: MenuVersionsComparisonProps) {
    const {onClose} = props;

    const {data: draft, isLoading: isDraftLoading, isError: isDraftError} = useQuery<MenuVersionManageDto>({
        queryKey: ["menu-versions", "draft"],
        queryFn: () => menuService.getDraftMenuVersion().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    const {data: published, isLoading: isPublishedLoading, isError: isPublishedError} = useQuery<MenuVersionManageDto>({
        queryKey: ["menu-versions", "published"],
        queryFn: () => menuService.getPublishedMenuVersion().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    const {
        data: archived,
        isLoading: isArchivedLoading,
        isError: isArchivedError
    } = useQuery<MenuVersionArchivedListDto[]>({
        queryKey: ["menu-versions", "archived"],
        queryFn: () => menuService.getArchivedMenuVersions().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    const [selectedArchivedVersion, setSelectedArchivedVersion] = useState<MenuVersionArchivedListDto | null>(null);

    if (isDraftLoading || isPublishedLoading || isArchivedLoading) return <LoadingSpinner/>
    if (isDraftError || isPublishedError || isArchivedError) return <div>Błąd podczas pobierania menu.</div>

    return (
        <div style={{
            width: "100%",
        }}>
            <MenuArchivedVersionsBar
                archivedVersions={archived!}
                selectedArchivedVersion={selectedArchivedVersion}
                setSelectedArchivedVersion={setSelectedArchivedVersion}
            />
            <Divider/>

            <MenuVersionsComparisonPanels
                draft={draft!}
                selectedArchived={selectedArchivedVersion}
                published={published!}
            />

            <MenuVersionsComparisonButtons
                onClose={onClose}
                selectedVersionId={selectedArchivedVersion?.id ?? 'todo'}
            />
        </div>
    )

}

export default MenuVersionsComparison;