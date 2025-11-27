import SidebarButton from "./SidebarButton.tsx";
import {useLocation} from "react-router-dom";
import {Home} from "lucide-react";

function SidebarMenu() {
    const location = useLocation();

    const sidebarItems = [
        {text: "Dashboard", icon: Home, path: "/dashboard"},
        {text: "Tables", icon: Home, path: "/tables"},
        {text: "Ingredients", icon: Home, path: "/ingredients"},
        {text: "Dishes", icon: Home, path: "/dishes"},
        {text: "Menu", icon: Home, path: "/menu"},
    ]

    return (
        <ul className="sidebar-menu">
            {sidebarItems.map((item) => (
                <li key={item.text}>
                    <SidebarButton
                        text={item.text}
                        icon={item.icon}
                        isSelected={location.pathname.startsWith(item.path)}
                        path={item.path}
                    />
                </li>
            ))}
        </ul>
    );
}

export default SidebarMenu;