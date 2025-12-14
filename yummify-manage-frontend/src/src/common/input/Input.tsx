import React from "react";
import "./Input.css"

export interface InputProps extends React.InputHTMLAttributes<HTMLInputElement> {
    label?: string;
    labelPosition?: "top" | "left";
    placeholder?: string;
}

function Input(props: InputProps) {
    const {label, labelPosition = "top", placeholder, id, ...rest} = props;

    return (
        <label className={`input label-${labelPosition}`} htmlFor={id}>
            {label && <span className="input-label">{label}</span>}
            <input placeholder={placeholder} id={id} {...rest} />
        </label>
    );
}

export default Input;