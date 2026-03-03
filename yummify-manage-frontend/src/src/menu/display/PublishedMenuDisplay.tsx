import {useQuery} from "@tanstack/react-query";
import {Dtos} from "../../common/dtos.ts";
import {menuService} from "../service/menuService.ts";
import MenuVersionClientDto = Dtos.MenuVersionClientDto;
import {useState} from "react";
import MenuSectionsBar from "./section/MenuSectionsBar.tsx";
import MenuEntryList from "./entry/MenuEntryList.tsx";
import "./PublishedMenuDisplay.css";
import LoadingSpinner from "../../common/loading/LoadingSpinner.tsx";

function PublishedMenuDisplay() {
    const {data, isLoading, isError} = useQuery<MenuVersionClientDto>({
        queryKey: ["menu", "versions", "published"],
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
        <div className="published-menu-display">
            <MenuSectionsBar
                sections={sections}
                activeSectionId={activeSection.id}
                setActiveSectionId={setActiveSectionId}
            />

            <MenuEntryList entries={activeSection.entries} />
        </div>
    );
}

export default PublishedMenuDisplay;