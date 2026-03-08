import {Controller, FieldPath, FieldValues, useFormContext} from "react-hook-form";
import SelectInput from "../../input/SelectInput.tsx";

export interface AppFormSelectFieldProps<Object extends FieldValues, Option, Value> {
    name: FieldPath<Object>;
    label: string;
    labelPosition?: "top" | "left";
    placeholder?: string;
    options: Option[]
    getOptionLabel: (option: Option) => string
    getOptionKey: (option: Option) => string
    getOptionValue: (option: Option) => Value
}

function AppFormSelectField<Object extends FieldValues, Option, Value>(props: AppFormSelectFieldProps<Object, Option, Value>) {
    const {name, label, labelPosition, placeholder, options, getOptionLabel, getOptionKey, getOptionValue} = props;

    const { control } = useFormContext<Object>();

    return (
        <Controller
            name={name}
            control={control}
            render={({ field }) => (
                <SelectInput
                    label={label}
                    labelPosition={labelPosition}
                    placeholder={placeholder}
                    selectedOption={field.value}
                    onChange={(values) => field.onChange(getOptionValue(values))}
                    options={options}
                    getOptionLabel={getOptionLabel}
                    getOptionKey={getOptionKey}
                />
            )}
        />
    );
}

export default AppFormSelectField;