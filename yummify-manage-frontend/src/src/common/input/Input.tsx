import React from "react";

export interface InputProps extends React.InputHTMLAttributes<HTMLInputElement> {
    label?: string;
    labelPosition?: "top" | "left";
    placeholder?: string;
}

function Input(props: InputProps) {
    const {label, labelPosition = "top", placeholder, id, ...rest} = props;

    return (
        <label className={`form-field ${labelPosition}`} htmlFor={id}>
            {label && <span className="form-label">{label}</span>}
            <input placeholder={placeholder} id={id} {...rest} />
        </label>
    );
}

export default Input;