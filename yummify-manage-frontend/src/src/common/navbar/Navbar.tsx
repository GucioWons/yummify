import {Bell, PanelLeftIcon, User} from "lucide-react";
import "./Navbar.css";
import {RestaurantContext} from "../../restaurant/context/RestaurantContext.tsx";
import {useContext} from "react";

function Navbar() {
    const { restaurant } = useContext(RestaurantContext);

    return (
        <header className="navbar">
            <div className="navbar-container">
                <button className="icon-button">
                    <PanelLeftIcon />
                </button>

                <div className="navbar-title">
                    <h1>{restaurant?.name}</h1>
                </div>

                <div className="navbar-actions">
                    <button className="icon-button">
                        <Bell />
                    </button>
                    <button className="icon-button">
                        <User />
                    </button>
                </div>
            </div>
        </header>
    );
}

export default Navbar;