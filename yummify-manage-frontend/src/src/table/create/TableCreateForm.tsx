import {useMutation, useQueryClient} from "@tanstack/react-query";
import {tableService} from "../service/tableService.ts";
import TableForm from "../form/TableForm.tsx";
import {Dtos} from "../../common/dtos.ts";
import TableDto = Dtos.TableDto;

export interface TableCreateFormProps {
    onCancel: () => void;
}

function TableCreateForm(props: TableCreateFormProps) {
    const {onCancel} = props;

    const queryClient = useQueryClient();

    const handleCreate = useMutation({
        mutationFn: (data: TableDto) => {
            return tableService.createTable(data);
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
        <TableForm onCancel={onCancel} handleSubmit={handleCreate.mutate} />
    );
}

export default TableCreateForm;