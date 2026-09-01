import React, {ForwardRefExoticComponent, RefAttributes} from "react";
import {LucideProps} from "lucide-react";

export interface CurrentOrderButtonProps {
    text: string;
    color: 'GREEN' | 'ORANGE' | 'BLUE' | 'PURPLE'
    onClick: () => void;
    icon: ForwardRefExoticComponent<Omit<LucideProps, "ref"> & RefAttributes<SVGSVGElement>>
}

function CurrentOrderButton(props: CurrentOrderButtonProps) {
    const {text, color, onClick, icon} = props;

    return (
        <button className={`current-order-button current-order-button-${color.toLowerCase()}`}>
            {React.createElement(icon, { width: 16, height: 16, strokeWidth: 2 })} {text}
        </button>
    )
}

export default CurrentOrderButton;