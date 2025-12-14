import {FieldPath, FieldValues, useFormContext} from "react-hook-form";

export interface AppFormTextFieldProps<T extends FieldValues> {
    name: FieldPath<T>;
    label: string;
    labelPosition?: "top" | "left";
    placeholder?: string;
}

function AppFormTextField<T extends FieldValues>(props: AppFormTextFieldProps<T>) {
    const { name, label, labelPosition = "top", placeholder } = props;

    const {register} = useFormContext<T>();

    return (
        <label className={`form-field ${labelPosition}`}>
            {label}
            <input placeholder={placeholder} {...register(name)} />
        </label>
    )
}

export default AppFormTextField;