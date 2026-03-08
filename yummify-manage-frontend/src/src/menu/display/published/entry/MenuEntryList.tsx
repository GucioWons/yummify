import {Dtos} from "../../../../common/dtos.ts";
import List from "../../../../common/list/List.tsx";
import MenuEntryListElement from "./MenuEntryListElement.tsx";
import MenuEntryDto = Dtos.MenuEntryDto;
import Button from "../../../../common/button/Button.tsx";
import {Plus} from "lucide-react";

export interface MenuEntryListProps {
    entries: MenuEntryDto[];
    isDraft?: boolean
    onAddEntryClick?: () => void;
}

function MenuEntryList(props: MenuEntryListProps) {
    const {entries, isDraft, onAddEntryClick} = props;

    return (
        <List
            items={entries}
            renderItem={(entry) => <MenuEntryListElement entry={entry}/>}
            firstElement={isDraft
                ? <Button text="Add Entry" icon={Plus} onClick={onAddEntryClick} />
                : null
            }
        />
    );
}

export default MenuEntryList