import {FieldPath, FieldValues, useFormContext} from "react-hook-form";
import TextInput from "../../input/TextInput.tsx";

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
        <TextInput
            label={label}
            labelPosition={labelPosition}
            placeholder={placeholder}
            {...register(name)}
        />
    );
}

export default AppFormTextField;