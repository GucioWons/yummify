import Select, {SingleValue} from "react-select";
import {InputProps} from "./Input.tsx";
import "./SelectInput.css"

export interface SelectInputProps<Option> extends Omit<InputProps, "onChange"> {
    options: Option[];
    selectedOption?: Option;
    onChange: (option: Option) => void;
    getOptionLabel: (option: Option) => string
}

function SelectInput<Option>(props: SelectInputProps<Option>) {
    const {
        label,
        labelPosition = "top",
        placeholder,
        options,
        selectedOption,
        onChange,
        getOptionLabel,
    } = props;

    const handleChange = (newValue: SingleValue<Option>) => {
        if (newValue) {
            onChange(newValue);
        }
    };

    return (
        <div className={`select container-${labelPosition}`}>
            <label>{label}</label>
            <Select
                options={options}
                value={selectedOption}
                onChange={handleChange}
                placeholder={placeholder}
                menuPortalTarget={document.body}
                styles={{
                    container: (base) => ({
                        ...base,
                        width: "100%"
                    }),
                    menuPortal: (base) => ({
                        ...base,
                        zIndex: 9999
                    }),
                    menu: (base) => ({
                        ...base,
                        borderRadius: 8,
                        boxShadow: "0 4px 16px rgba(0,0,0,0.2)",
                        overflow: "visible",
                        padding: 4
                    }),
                    control: (base) => ({
                        ...base,
                        border: "2px solid #a7f3d0",
                        borderRadius: "8px",
                        boxShadow: "none",
                        fontSize: "14px",
                        fontFamily: "Inter, sans-serif"
                    }),
                    option: (base, state) => ({
                        ...base,
                        borderRadius: "8px",
                        padding: 4,
                        color: "black",
                        textAlign: "left",
                        backgroundColor: state.isFocused ? "#d1fae5" : "#ffffff",
                    }),
                    valueContainer: (base) => ({
                        ...base,
                        textAlign: "left",
                        flexWrap: "wrap",
                        padding: "8px",
                    }),
                    input: (base) => ({
                        ...base,
                        margin: 0,
                        padding: 0,
                        fontSize: "14px",
                    }),
                    indicatorsContainer: (base) => ({
                        ...base,
                        alignItems: "center",
                    })
                }}
                isSearchable={false}
                getOptionLabel={(option) => getOptionLabel(option)}
            />
        </div>
    );
}

export default SelectInput;