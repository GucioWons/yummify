import {Controller, FieldPath, FieldValues, useFormContext} from "react-hook-form";
import Multiselect from "../../input/Multiselect.tsx";

export interface AppFormMultiselectFieldProps<T extends FieldValues, U> {
    name: FieldPath<T>;
    label: string;
    labelPosition?: "top" | "left";
    placeholder?: string;
    options: U[]
    getOptionLabel: (option: U) => string
    getOptionKey: (option: U) => string
}

function AppFormMultiselectField<T extends FieldValues, U>(props: AppFormMultiselectFieldProps<T, U>) {
    const {name, label, labelPosition, placeholder, options, getOptionLabel, getOptionKey} = props;

    const { control } = useFormContext<T>();

    return (
        <Controller
            name={name}
            control={control}
            render={({ field }) => (
                <Multiselect
                    label={label}
                    labelPosition={labelPosition}
                    placeholder={placeholder}
                    value={field.value}
                    onChange={(values) => field.onChange(values)}
                    options={options}
                    getOptionLabel={getOptionLabel}
                    getOptionValue={getOptionKey}
                />
            )}
        />
    );
}

export default AppFormMultiselectField;