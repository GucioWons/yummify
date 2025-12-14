import {FieldPath, FieldValues, useFormContext} from "react-hook-form";

export interface AppFormTextFieldProps<T extends FieldValues> {
    name: FieldPath<T>;
    label: string;
    labelPosition?: "top" | "left";
}

function AppFormTextField<T extends FieldValues>(props: AppFormTextFieldProps<T>) {
    const { name, label, labelPosition = "top" } = props;

    const {register} = useFormContext<T>();

    return (
        <label className={`form-field ${labelPosition}`}>
            {label}
            <input {...register(name)} />
        </label>
    )
}

export default AppFormTextField;