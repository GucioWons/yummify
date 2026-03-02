import {Dtos} from "../../common/dtos.ts";
import AppForm from "../../common/form/AppForm.tsx";
import AppFormTextField from "../../common/form/fields/AppFormTextField.tsx";
import AppFormNumberField from "../../common/form/fields/AppFormNumberField.tsx";
import TableDto = Dtos.TableDto;

export interface TableFormProps {
    table?: TableDto;
    onCancel: () => void;
    handleSubmit: (data: TableDto) => void;
}

function TableForm(props: TableFormProps) {
    const {table, onCancel, handleSubmit} = props;

    return (
        <AppForm
            <TableDto>
            initialData={table ?? {}}
            onSubmit={handleSubmit}
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