import {Bell, PanelLeftIcon, User} from "lucide-react";
import "./Navbar.css";
import {RestaurantContext} from "../../../restaurant/context/RestaurantContext.tsx";
import {useContext} from "react";

export interface NavbarProps {
    sidebarOpen: boolean;
    toggleSidebarOpen: () => void;
}

function Navbar(props: NavbarProps) {
    const {restaurant} = useContext(RestaurantContext);

    return (
        <header className={`navbar ${props.sidebarOpen ? "sidebar-open" : ""}`}>
            <div className="navbar-container">
                <button
                    onClick={() => props.toggleSidebarOpen()}
                    className="icon-button"
                >
                    <PanelLeftIcon/>
                </button>

                <div className="navbar-title">
                    <h1>{restaurant?.name}</h1>
                </div>

                <div className="navbar-actions">
                    <button className="icon-button">
                        <Bell/>
                    </button>
                    <button className="icon-button">
                        <User/>
                    </button>
                </div>
            </div>
        </header>
    );
}

export default Navbar;