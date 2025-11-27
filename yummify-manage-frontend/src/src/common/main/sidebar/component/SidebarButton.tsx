import React, {ForwardRefExoticComponent, RefAttributes} from "react";
import {LucideProps} from "lucide-react";
import {useNavigate} from "react-router-dom";

export interface SidebarButtonProps {
    text: string
    icon: ForwardRefExoticComponent<Omit<LucideProps, "ref"> & RefAttributes<SVGSVGElement>>
    isSelected: boolean;
    path: string
}

function SidebarButton(props: SidebarButtonProps) {
    const {text, icon, isSelected, path} = props;

    const navigate = useNavigate();

    return (
        <button className={`sidebar-button ${isSelected ? "selected" : ""}`} onClick={() => navigate(path)}>
            <div className="sidebar-button-container">
                {React.createElement(icon, { strokeWidth: isSelected ? 2 : 1 })}
                {text}
            </div>
        </button>
    )
}

export default SidebarButton;