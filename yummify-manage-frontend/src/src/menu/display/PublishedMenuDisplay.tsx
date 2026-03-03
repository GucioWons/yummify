import {useQuery} from "@tanstack/react-query";
import {Dtos} from "../../common/dtos.ts";
import {menuService} from "../service/menuService.ts";
import MenuVersionClientDto = Dtos.MenuVersionClientDto;
import {useState} from "react";
import MenuSectionsBar from "./section/MenuSectionsBar.tsx";
import MenuEntryList from "./entry/MenuEntryList.tsx";
import "./PublishedMenuDisplay.css";

function PublishedMenuDisplay() {
    const {data, isLoading, isError} = useQuery<MenuVersionClientDto>({
        queryKey: ["menu", "versions", "published"],
        queryFn: () => menuService.getPublishedMenuVersion().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    const [activeSectionId, setActiveSectionId] = useState<string | null>(null);

    if (isLoading) return <div>Ładowanie...</div>;
    if (isError) return <div>Błąd podczas pobierania składniku.</div>;

    const sections = data!.sections
        .sort((a, b) => a.position - b.position);

    return (
        <div className="published-menu-display">
            <MenuSectionsBar
                sections={sections}
                activeSectionId={activeSectionId}
                setActiveSectionId={setActiveSectionId}
            />

            <MenuEntryList entries={sections.find(s => s.id === activeSectionId)?.entries ?? []} />
        </div>
    );
}

export default PublishedMenuDisplay;