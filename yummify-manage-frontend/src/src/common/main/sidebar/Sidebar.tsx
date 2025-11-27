import "./Sidebar.css";
import SidebarButton from "./component/SidebarButton.tsx";
import {Home} from "lucide-react";
import {useLocation} from "react-router-dom";

export interface SidebarProps {
    isOpen: boolean;
}

function Sidebar(props: SidebarProps) {
    const location = useLocation();

    const sidebarItems = [
        {text: "Dashboard", icon: Home, path: "/dashboard"},
        {text: "Tables", icon: Home, path: "/tables"},
        {text: "Ingredients", icon: Home, path: "/ingredients"},
        {text: "Dishes", icon: Home, path: "/dishes"},
        {text: "Menu", icon: Home, path: "/menu"},
    ]

    return (
        <div className={`sidebar ${props.isOpen ? "open" : ""}`}>
            <div className="sidebar-content">
                <ul className="sidebar-menu">
                    {sidebarItems.map((item) => (
                        <li className="sidebar-menu-item" key={item.text}>
                            <SidebarButton
                                text={item.text}
                                icon={item.icon}
                                isSelected={location.pathname.startsWith(item.path)}
                                path={item.path}
                            />
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}

export default Sidebar;