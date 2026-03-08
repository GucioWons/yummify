import {useQuery} from "@tanstack/react-query";
import {Dtos} from "../../common/dtos.ts";
import {menuService} from "../service/menuService.ts";
import {useState} from "react";
import MenuSectionsBar from "../section/display/MenuSectionsBar.tsx";
import MenuEntryList from "../entry/list/MenuEntryList.tsx";
import LoadingSpinner from "../../common/loading/LoadingSpinner.tsx";
import MenuVersionManageDto = Dtos.MenuVersionManageDto;

function PublishedMenuDisplay() {
    const {data, isLoading, isError} = useQuery<MenuVersionManageDto>({
        queryKey: ["menu-versions", "published"],
        queryFn: () => menuService.getPublishedMenuVersion().then(res => res.data),
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
        <div className="menu-display">
            <MenuSectionsBar
                sections={sections}
                activeSectionId={activeSection.id}
                setActiveSectionId={setActiveSectionId}
            />

            <MenuEntryList sectionId={activeSection.id} entries={activeSection.entries} />
        </div>
    );
}

export default PublishedMenuDisplay;