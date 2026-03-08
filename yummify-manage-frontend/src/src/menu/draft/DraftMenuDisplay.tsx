import {useQuery} from "@tanstack/react-query";
import {menuService} from "../service/menuService.ts";
import {useState} from "react";
import LoadingSpinner from "../../common/loading/LoadingSpinner.tsx";
import {Dtos} from "../../common/dtos.ts";
import MenuVersionManageDto = Dtos.MenuVersionManageDto;
import MenuEntryList from "../entry/list/MenuEntryList.tsx";
import DraftMenuInfoTile from "./DraftMenuInfoTile.tsx";
import "./DraftMenuDisplay.css";
import MenuSectionNamesModal from "../section/display/MenuSectionNamesModal.tsx";
import MenuSectionCreateModal from "../section/create/MenuSectionCreateModal.tsx";
import MenuSectionsBar from "../section/display/MenuSectionsBar.tsx";

function DraftMenuDisplay() {
    const {data, isLoading, isError} = useQuery<MenuVersionManageDto>({
        queryKey: ["menu-versions", "draft"],
        queryFn: () => menuService.getDraftMenuVersion().then(res => res.data),
        staleTime: 1000 * 60 * 5,
    });

    const [activeSectionId, setActiveSectionId] = useState<string | null>(null);

    const [isNamesModalOpen, setIsNamesModalOpen] = useState(false);
    const [isAddSectionModalOpen, setIsAddSectionModalOpen] = useState(false);

    if (isLoading) return <LoadingSpinner />;
    if (isError) return <div>Błąd podczas pobierania składniku.</div>;

    const sections = [...data!.sections].sort(
        (a, b) => a.position - b.position
    );

    const activeSection = sections.find(s => s.id === activeSectionId) ?? sections[0];

    return (
        <div className="menu-display">
            <DraftMenuInfoTile />
            <MenuSectionsBar
                sections={sections}
                activeSectionId={activeSection.id}
                setActiveSectionId={setActiveSectionId}
                isDraft
                onSectionNamesButtonClick={() => setIsNamesModalOpen(true)}
                onAddSectionButtonClick={() => setIsAddSectionModalOpen(true)}
            />
            <MenuEntryList entries={activeSection.entries} sectionId={activeSection.id} isDraft />
            {isNamesModalOpen && <MenuSectionNamesModal sections={sections} onClose={() => setIsNamesModalOpen(false)} />}
            {isAddSectionModalOpen && <MenuSectionCreateModal onClose={() => setIsAddSectionModalOpen(false)} />}
        </div>
    )
}

export default DraftMenuDisplay;