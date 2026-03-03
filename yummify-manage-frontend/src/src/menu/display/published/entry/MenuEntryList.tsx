import {Dtos} from "../../../../common/dtos.ts";
import List from "../../../../common/list/List.tsx";
import MenuEntryListElement from "./MenuEntryListElement.tsx";
import MenuEntryDto = Dtos.MenuEntryDto;

export interface MenuEntryListProps {
    entries: MenuEntryDto[]
}

function MenuEntryList(props: MenuEntryListProps) {
    const {entries} = props;

    return (
        <List
            items={entries}
            renderItem={(entry) => <MenuEntryListElement entry={entry}/>}
        />
    );
}

export default MenuEntryList