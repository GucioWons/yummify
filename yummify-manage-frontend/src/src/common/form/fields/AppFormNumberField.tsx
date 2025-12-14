import {FieldPath, FieldValues, useFormContext} from "react-hook-form";

export interface AppFormNumberFieldProps<T extends FieldValues> {
    name: FieldPath<T>;
    label: string;
    labelPosition?: "top" | "left";
    placeholder?: string;
}

function AppFormNumberField<T extends FieldValues>(props: AppFormNumberFieldProps<T>) {
    const { name, label, labelPosition = "top", placeholder } = props;

    const {register} = useFormContext<T>();

    return (
        <label className={`form-field ${labelPosition}`}>
            {label}
            <input placeholder={placeholder} type="number" {...register(name)} />
        </label>
    )
}

export default AppFormNumberField;