import "./Sidebar.css";

export interface SidebarProps {
    isOpen: boolean;
}

function Sidebar(props: SidebarProps) {
    return (
        <div className={`sidebar ${props.isOpen ? "open" : ""}`}>
            <div className="sidebar-content">
                <ul className="sidebar-links">
                    <li><a href="/public">Home</a></li>
                    <li><a href="/menu">Menu</a></li>
                    <li><a href="/profile">Profile</a></li>
                </ul>
            </div>
        </div>
    );
}

export default Sidebar;