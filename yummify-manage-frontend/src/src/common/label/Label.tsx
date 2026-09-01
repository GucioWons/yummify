import React, {ForwardRefExoticComponent, RefAttributes} from "react";
import {LucideProps} from "lucide-react";
import './Label.css'

export interface LabelProps {
    text: string;
    color: 'BLUE' | 'YELLOW' | 'ORANGE' | 'GREEN' | 'RED' | 'GREY' | 'PURPLE'
    icon?: ForwardRefExoticComponent<Omit<LucideProps, "ref"> & RefAttributes<SVGSVGElement>>
}

function Label(props: LabelProps) {
    const { text, color, icon } = props;

    return (
        <div className={`label label-${color.toLowerCase()}`}>
            {icon && React.createElement(icon, { width: 16, height: 16, strokeWidth: 2 })} {text}
        </div>
    );
}

export default Label;