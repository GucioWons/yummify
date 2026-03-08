import {Dtos} from "../../../../common/dtos.ts";
import List from "../../../../common/list/List.tsx";
import MenuEntryListElement from "./MenuEntryListElement.tsx";
import Button from "../../../../common/button/Button.tsx";
import {Plus} from "lucide-react";
import MenuEntryCreateModal from "../../draft/MenuEntryCreateModal.tsx";
import {useState} from "react";
import MenuEntryUpdateModal from "../../draft/MenuEntryUpdateModal.tsx";
import MenuEntryDto = Dtos.MenuEntryDto;

export interface MenuEntryListProps {
    sectionId: string;
    entries: MenuEntryDto[];
    isDraft?: boolean
}

function MenuEntryList(props: MenuEntryListProps) {
    const {sectionId, entries, isDraft} = props;

    const [isAddEntryModalOpen, setIsAddEntryModalOpen] = useState(false)
    const [entryToEdit, setEntryToEdit] = useState<MenuEntryDto | null>(null);

    return (
        <>
            <List
                items={entries}
                renderItem={(entry) => <MenuEntryListElement entry={entry} isEditable={isDraft} onEditClick={setEntryToEdit}/>}
                firstElement={isDraft
                    ? <Button text="Add Entry" icon={Plus} onClick={() => setIsAddEntryModalOpen(true)} />
                    : null
                }
            />
            {isAddEntryModalOpen && <MenuEntryCreateModal sectionId={sectionId} onClose={() => setIsAddEntryModalOpen(false)} />}
            {entryToEdit && <MenuEntryUpdateModal entry={entryToEdit} sectionId={sectionId} onClose={() => setEntryToEdit(null)} />}
        </>
    );
}

export default MenuEntryList