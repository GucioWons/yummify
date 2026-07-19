import {Dtos} from "../../common/dtos.ts";
import TableDto = Dtos.TableDto;
import Display from "../../common/display/Display.tsx";
import TextFieldDisplay from "../../common/display/fields/TextFieldDisplay.tsx";
import Button from "../../common/button/Button.tsx";
import {QrCode} from "lucide-react";
import TableOtpDto = Dtos.TableOtpDto;
import {useMutation} from "@tanstack/react-query";
import {tableService} from "../service/tableService.ts";

export interface TableDisplayProps {
    table: TableDto
    onEditClick: () => void;
    onCloseClick: () => void;
    onQrCodeChange: (qrCode: TableOtpDto) => void;
}

function TableDisplay(props: TableDisplayProps) {
    const {table, onCloseClick, onEditClick, onQrCodeChange} = props;

    const handleCreate = useMutation({
        mutationFn: () => tableService.generateOtp(table.id),
        onSuccess: (data) => onQrCodeChange(data.data),
        onError: (error) => console.error("Unexpected error:", error),
    });

    return (
        <Display
            onEditClick={onEditClick}
            onCloseClick={onCloseClick}
            additionalButtons={
                <Button
                    outlined
                    text="Generate QR Code"
                    icon={QrCode}
                    onClick={handleCreate.mutate}
                />
            }
        >
            <TextFieldDisplay label='Name' text={table.name}/>
            <TextFieldDisplay label='Capacity' text={`${table.name}`}/>
        </Display>
    )
}

export default TableDisplay;