import {Bell, PanelLeftIcon, User} from "lucide-react";
import "./Navbar.css";
import {RestaurantContext} from "../../../restaurant/context/RestaurantContext.tsx";
import {useContext} from "react";
import NavbarIconButton from "./component/NavbarIconButton.tsx";
import NavbarTitle from "./component/NavbarTitle.tsx";

export interface NavbarProps {
    sidebarOpen: boolean;
    toggleSidebarOpen: () => void;
}

function Navbar(props: NavbarProps) {
    const {sidebarOpen, toggleSidebarOpen} = props;

    const {restaurant} = useContext(RestaurantContext);

    return (
        <header className={`navbar ${sidebarOpen ? "sidebar-open" : ""}`}>
            <div className="navbar-container">
                <NavbarIconButton onClick={toggleSidebarOpen} icon={PanelLeftIcon} />
                <NavbarTitle restaurantName={restaurant?.name} />

                <div className="navbar-actions">
                    <NavbarIconButton onClick={() => {}} icon={Bell} />
                    <NavbarIconButton onClick={() => {}} icon={User} />
                </div>
            </div>
        </header>
    );
}

export default Navbar;