import {FieldPath, FieldValues, useFormContext} from "react-hook-form";
import NumberInput from "../../input/NumberInput.tsx";

export interface AppFormNumberFieldProps<T extends FieldValues> {
    name: FieldPath<T>;
    label: string;
    labelPosition?: "top" | "left";
    placeholder?: string;
}

function AppFormNumberField<T extends FieldValues>(props: AppFormNumberFieldProps<T>) {
    const { name, label, labelPosition, placeholder } = props;

    const {register} = useFormContext<T>();

    return (
        <NumberInput
            label={label}
            labelPosition={labelPosition}
            placeholder={placeholder}
            {...register(name)}
        />
    );
}

export default AppFormNumberField;