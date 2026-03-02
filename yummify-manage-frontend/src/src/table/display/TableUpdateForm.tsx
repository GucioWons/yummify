import {Dtos} from "../../common/dtos.ts";
import TableForm from "../form/TableForm.tsx";
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {tableService} from "../service/tableService.ts";
import TableDto = Dtos.TableDto;

export interface TableUpdateFormProps {
    table: TableDto;
    onCancel: () => void;
}

function TableUpdateForm(props: TableUpdateFormProps) {
    const {table, onCancel} = props;

    const queryClient = useQueryClient();

    const handleUpdate = useMutation({
        mutationFn: (data: TableDto) => {
            return tableService.updateTable(data);
        },
        onSuccess: () => {
            queryClient.invalidateQueries({queryKey: ["tables"]})
                .then(() => onCancel());
        },
        onError: (error) => {
            console.error("Unexpected error:", error);
        },
    });

    return (
        <TableForm table={table} onCancel={onCancel} handleSubmit={handleUpdate.mutate}/>
    );
}

export default TableUpdateForm;