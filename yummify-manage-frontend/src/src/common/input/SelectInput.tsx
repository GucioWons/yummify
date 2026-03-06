import Select, {SingleValue} from "react-select";

export interface SelectInputProps<T> {
    options: T[];
    selectedOption?: T;
    onChange: (option: T) => void;
    getOptionLabel: (option: T) => string
    placeholder: string;
}

function SelectInput<T>(props : SelectInputProps<T>) {
    const {options, selectedOption, onChange, getOptionLabel, placeholder} = props;

    const handleChange = (newValue: SingleValue<T>) => {
        if (newValue) {
            onChange(newValue);
        }
    };

    return (
        <Select
            options={options}
            value={selectedOption}
            onChange={handleChange}
            placeholder={placeholder}
            menuPortalTarget={document.body}
            styles={{
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
    );
}

export default SelectInput;