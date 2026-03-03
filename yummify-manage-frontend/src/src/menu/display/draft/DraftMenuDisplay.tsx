import DraftMenuSectionBar from "./section/DraftMenuSectionBar.tsx";
import {useQuery} from "@tanstack/react-query";
import {menuService} from "../../service/menuService.ts";
import {useState} from "react";
import LoadingSpinner from "../../../common/loading/LoadingSpinner.tsx";
import {Dtos} from "../../../common/dtos.ts";
import MenuVersionManageDto = Dtos.MenuVersionManageDto;
import MenuEntryList from "../published/entry/MenuEntryList.tsx";
import DraftMenuInfoTile from "./DraftMenuInfoTile.tsx";

function DraftMenuDisplay() {
    const {data, isLoading, isError} = useQuery<MenuVersionManageDto>({
        queryKey: ["menu", "versions", "draft"],
        queryFn: () => menuService.getDraftMenuVersion().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    const [activeSectionId, setActiveSectionId] = useState<string | null>(null);

    if (isLoading) return <LoadingSpinner />;
    if (isError) return <div>Błąd podczas pobierania składniku.</div>;

    const sections = [...data!.sections].sort(
        (a, b) => a.position - b.position
    );

    const activeSection = sections.find(s => s.id === activeSectionId) ?? sections[0];

    return (
        <div>
            <DraftMenuInfoTile />
            <DraftMenuSectionBar sections={sections} activeSectionId={activeSection.id} setActiveSectionId={setActiveSectionId} />
            <MenuEntryList entries={activeSection.entries} />
        </div>
    )
}

export default DraftMenuDisplay;