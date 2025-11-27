import SidebarButton from "./SidebarButton.tsx";
import {useLocation} from "react-router-dom";
import {BookOpen, ChefHat, CookingPot, Home, RockingChair} from "lucide-react";

function SidebarMenu() {
    const location = useLocation();

    const sidebarItems = [
        {text: "Dashboard", icon: Home, path: "/dashboard"},
        {text: "Tables", icon: RockingChair, path: "/tables"},
        {text: "Ingredients", icon: CookingPot, path: "/ingredients"},
        {text: "Dishes", icon: ChefHat, path: "/dishes"},
        {text: "Menu", icon: BookOpen, path: "/menu"},
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