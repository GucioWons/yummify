import {InputProps} from "./Input.tsx";
import Select, {CSSObjectWithLabel} from "react-select";
import "./Multiselect.css"

export interface MultiselectProps<Option> extends Omit<InputProps, "onChange"> {
    options: Option[]
    selectedOptions: Option[];
    onChange: (values: Option[]) => void;
    getOptionLabel: (option: Option) => string
    getOptionKey: (option: Option) => string
}

function Multiselect<Option>(props: MultiselectProps<Option>) {
    const {
        label,
        labelPosition = "top",
        placeholder,
        selectedOptions,
        onChange,
        options,
        getOptionLabel,
        getOptionKey,
    } = props;

    function getControlStyle(base: CSSObjectWithLabel): CSSObjectWithLabel {
        return {
            ...base,
            border: "2px solid #a7f3d0",
            borderRadius: "8px",
            boxShadow: "none",
            fontSize: "14px",
            fontFamily: "Inter, sans-serif",
            width: "100%"
        };
    }

    function getValueContainerStyle(base: CSSObjectWithLabel): CSSObjectWithLabel {
        return {
            ...base,
            flexWrap: "wrap",
            padding: "8px",
        };
    }

    function getInputStyle(base: CSSObjectWithLabel): CSSObjectWithLabel {
        return {
            ...base,
            margin: 0,
            padding: 0,
            fontSize: "14px",
        };
    }

    function getPlaceholderStyle(base: CSSObjectWithLabel): CSSObjectWithLabel {
        return {
            ...base,
            fontSize: "14px",
        };
    }

    function getIndicatorsContainerStyle(base: CSSObjectWithLabel): CSSObjectWithLabel {
        return {
            ...base,
            alignItems: "center",
        };
    }

    function getOptionStyle(base: CSSObjectWithLabel, isFocused: boolean): CSSObjectWithLabel {
        return {
            ...base,
            borderRadius: "8px",
            padding: 4,
            color: "black",
            textAlign: "left",
            backgroundColor: isFocused ? "#d1fae5" : "#ffffff",
        };
    }

    function getMultiValueStyle(base: CSSObjectWithLabel): CSSObjectWithLabel {
        return {
            ...base,
            color: "#059669",
            backgroundColor: "#a7f3d0",
            border: "1px solid #059669",
            borderRadius: "8px"
        };
    }

    function getMultiValueLabelStyle(base: CSSObjectWithLabel): CSSObjectWithLabel {
        return {
            ...base,
            color: "#059669",
        };
    }

    function getMultiValueRemoveStyle(base: CSSObjectWithLabel): CSSObjectWithLabel {
        return {
            ...base,
            borderRadius: "8px",
        };
    }

    return (
        <div className={`multiselect container-${labelPosition}`}>
            <label>{label}</label>

            <Select
                isMulti
                className={"multiselect input"}
                classNamePrefix={"multiselect"}
                value={selectedOptions}
                onChange={(selected) => onChange([...selected])}
                options={options}
                placeholder={placeholder}
                getOptionLabel={getOptionLabel}
                getOptionValue={getOptionKey}
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
                    control: (base) => getControlStyle(base),
                    valueContainer: (base) => getValueContainerStyle(base),
                    input: (base) => getInputStyle(base),
                    placeholder: (base) => getPlaceholderStyle(base),
                    indicatorsContainer: (base) => getIndicatorsContainerStyle(base),
                    option: (base, state) => getOptionStyle(base, state.isFocused),
                    multiValue: (base) => getMultiValueStyle(base),
                    multiValueLabel: (base) => getMultiValueLabelStyle(base),
                    multiValueRemove: (base) => getMultiValueRemoveStyle(base)
                }}
            />
        </div>
    );
}

export default Multiselect;