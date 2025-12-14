import React, {ForwardRefExoticComponent, RefAttributes} from "react";
import {LucideProps} from "lucide-react";
import "./Button.css";

export interface ButtonProps {
    text?: string;
    icon?: ForwardRefExoticComponent<Omit<LucideProps, "ref"> & RefAttributes<SVGSVGElement>>
    onClick?: () => void;
    type?: "submit" | "reset" | "button";
    outlined?: boolean
}

function Button(props: ButtonProps) {
    const { text, icon, onClick, type, outlined} = props;

    return (
        <button
            className={`custom-button ${outlined ? "outlined" : ""}`}
            onClick={onClick}
            type={type}
        >
            {icon && React.createElement(icon)}
            {text && <span>{text}</span>}
        </button>
    );
}

export default Button;