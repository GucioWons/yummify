import "./Sidebar.css";
import SidebarMenu from "./component/SidebarMenu.tsx";

export interface SidebarProps {
    isOpen: boolean;
}

function Sidebar(props: SidebarProps) {
    return (
        <div className={`sidebar ${props.isOpen ? "open" : ""}`}>
            <SidebarMenu />
        </div>
    );
}

export default Sidebar;