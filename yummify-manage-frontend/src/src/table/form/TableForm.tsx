import {DTOs} from "../../common/dtos.ts";
import TableDTO = DTOs.TableDTO;
import AppForm from "../../common/form/AppForm.tsx";
import AppFormTextField from "../../common/form/fields/AppFormTextField.tsx";
import AppFormNumberField from "../../common/form/fields/AppFormNumberField.tsx";
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {tableService} from "../service/tableService.ts";

export interface TableFormProps {
    table?: TableDTO;
    onCancel: () => void;
}

function TableForm(props: TableFormProps) {
    const {table, onCancel} = props;

    const queryClient = useQueryClient();

    const handleSubmit = useMutation({
        mutationFn: (data: TableDTO) => {
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
        <AppForm
            <TableDTO>
            initialData={table ?? {}}
            onSubmit={(data) => handleSubmit.mutate(data)}
            onCancel={onCancel}
        >
            <AppFormTextField
                name="name"
                label="Table number"
                placeholder="Eg. T9"
            />

            <AppFormNumberField
                name="capacity"
                label="Capacity"
                placeholder="0"
            />
        </AppForm>
    );
}

export default TableForm;