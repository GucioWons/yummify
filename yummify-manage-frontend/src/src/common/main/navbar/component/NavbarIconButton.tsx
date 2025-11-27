import {LucideProps} from "lucide-react";
import React, {ForwardRefExoticComponent, RefAttributes} from "react";

export interface NavbarIconButtonProps {
    onClick: () => void;
    icon: ForwardRefExoticComponent<Omit<LucideProps, "ref"> & RefAttributes<SVGSVGElement>>
}

function NavbarIconButton(props: NavbarIconButtonProps) {
    const {onClick, icon} = props;

    return (
        <button
            onClick={() => onClick()}
            className="icon-button"
        >
            {React.createElement(icon)}
        </button>
    );
}

export default NavbarIconButton;