import {Dtos} from "../../common/dtos.ts";
import MenuVersionManageDto = Dtos.MenuVersionManageDto;
import {useQuery} from "@tanstack/react-query";
import {menuService} from "../service/menuService.ts";
import LoadingSpinner from "../../common/loading/LoadingSpinner.tsx";
import MenuVersionArchivedListDto = Dtos.MenuVersionArchivedListDto;
import MenuArchivedVersionsBar from "./MenuVersionsBar.tsx";
import {useState} from "react";
import MenuVersionsTemp from "./MenuVersionsTemp.tsx";
import MenuVersionsTemp2 from "./MenuVersionsTemp2.tsx";
import Divider from "../../common/divider/Divider.tsx";
import AppFormCancelButton from "../../common/form/buttons/AppFormCancelButton.tsx";
import AppFormSubmitButton from "../../common/form/buttons/AppFormSubmitButton.tsx";

function MenuVersionsComparison() {
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

    const {data: archived, isLoading: isArchivedLoading, isError: isArchivedError} = useQuery<MenuVersionArchivedListDto[]>({
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
                setSelectedArchivedVersion={setSelectedArchivedVersion}
            />
            <Divider/>
            <div style={{display: "flex"}}>
                <MenuVersionsTemp menuVersion={draft!} title="Current"/>
                <MenuVersionsTemp2 selectedArchivedVersion={selectedArchivedVersion} publishedVersion={published!}/>
            </div>
        </div>
    )

}

export default MenuVersionsComparison;