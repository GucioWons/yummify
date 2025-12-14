import {DTOs} from "../../common/dtos.ts";
import TableDTO = DTOs.TableDTO;
import AppForm from "../../common/form/AppForm.tsx";
import AppFormTextField from "../../common/form/fields/AppFormTextField.tsx";
import AppFormNumberField from "../../common/form/fields/AppFormNumberField.tsx";

export interface TableFormProps {
    table?: TableDTO;
    onCancel: () => void;
}

function TableForm(props: TableFormProps) {
    const {table, onCancel} = props;

    return (
        <AppForm
            initialData={table ?? {}}
            onSubmit={(data) => console.log(data)}
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