import {useContext} from "react";
import {AuthContext} from "../auth/context/AuthContext.tsx";
import {RestaurantContext} from "../restaurant/context/RestaurantContext.tsx";
import {useTranslation} from "react-i18next";

function DashboardPage() {
    const {user, logout} = useContext(AuthContext);
    const {restaurant} = useContext(RestaurantContext);

    const {t} = useTranslation();

    return (
        <>
            <div>{t("welcome", {name: user?.username})}</div>
            <div>{restaurant?.name}</div>
            <button onClick={logout}>{t("logout")}</button>
        </>
    );
}

export default DashboardPage;