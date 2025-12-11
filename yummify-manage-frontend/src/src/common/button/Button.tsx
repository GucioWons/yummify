import React, {ForwardRefExoticComponent, RefAttributes} from "react";
import {LucideProps} from "lucide-react";
import "./Button.css";

export interface ButtonProps {
    text?: string;
    icon?: ForwardRefExoticComponent<Omit<LucideProps, "ref"> & RefAttributes<SVGSVGElement>>
    onClick?: () => void;
}

function Button(props: ButtonProps) {
    const { text, icon, onClick } = props;

    return (
        <button className="custom-button" onClick={onClick}>
            {icon && React.createElement(icon)}
            {text && <span>{text}</span>}
        </button>
    );
}

export default Button;