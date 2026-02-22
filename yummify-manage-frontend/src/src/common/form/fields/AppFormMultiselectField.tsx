import {Controller, FieldPath, FieldValues, useFormContext} from "react-hook-form";
import Multiselect from "../../input/Multiselect.tsx";

export interface AppFormMultiselectFieldProps<Object extends FieldValues, Option, Value> {
    name: FieldPath<Object>;
    label: string;
    labelPosition?: "top" | "left";
    placeholder?: string;
    options: Option[]
    getOptionLabel: (option: Option) => string
    getOptionKey: (option: Option) => string
    getOptionValue: (option: Option) => Value
}

function AppFormMultiselectField<Object extends FieldValues, Option, Value>(props: AppFormMultiselectFieldProps<Object, Option, Value>) {
    const {name, label, labelPosition, placeholder, options, getOptionLabel, getOptionKey, getOptionValue} = props;

    const { control } = useFormContext<Object>();

    return (
        <Controller
            name={name}
            control={control}
            render={({ field }) => (
                <Multiselect
                    label={label}
                    labelPosition={labelPosition}
                    placeholder={placeholder}
                    value={field.value ?? []}
                    onChange={(values) => field.onChange(values)}
                    options={options}
                    getOptionLabel={getOptionLabel}
                    getOptionKey={getOptionKey}
                    getOptionValue={getOptionValue}
                />
            )}
        />
    );
}

export default AppFormMultiselectField;