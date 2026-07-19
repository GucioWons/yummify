import {Dtos} from "../../common/dtos.ts";
import TableDto = Dtos.TableDto;
import Display from "../../common/display/Display.tsx";
import TextFieldDisplay from "../../common/display/fields/TextFieldDisplay.tsx";
import Button from "../../common/button/Button.tsx";
import {QrCode} from "lucide-react";

export interface TableDisplayProps {
    table: TableDto
    onEditClick: () => void;
    onCloseClick: () => void;
}

function TableDisplay(props: TableDisplayProps) {
    const {table, onCloseClick, onEditClick} = props;

    return (
        <Display
            onEditClick={onEditClick}
            onCloseClick={onCloseClick}
            additionalButtons={
                <Button
                    outlined
                    text="Generate QR Code"
                    icon={QrCode}
                />
            }
        >
            <TextFieldDisplay label='Name' text={table.name}/>
            <TextFieldDisplay label='Capacity' text={`${table.name}`}/>
        </Display>
    )
}

export default TableDisplay;