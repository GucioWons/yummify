import {Dtos} from "../../common/dtos.ts";
import TableDto = Dtos.TableDto;
import Display from "../../common/display/Display.tsx";
import TextFieldDisplay from "../../common/display/fields/TextFieldDisplay.tsx";

export interface TableDisplayProps {
    table: TableDto
    onEditClick: () => void;
    onCloseClick: () => void;
}

function TableDisplay(props : TableDisplayProps) {
    const {table, onCloseClick, onEditClick} = props;

    return (
        <Display onEditClick={onEditClick} onCloseClick={onCloseClick}>
            <TextFieldDisplay label='Name' text={table.name}/>
            <TextFieldDisplay label='Capacity' text={`${table.name}`}/>
        </Display>
    )
}

export default TableDisplay;