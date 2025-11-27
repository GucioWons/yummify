import './App.css'
import LoginPage from "./src/auth/page/LoginPage.tsx";
import {AuthContext} from "./src/auth/context/AuthContext.tsx";
import {useContext} from "react";
import "./src/common/i18n/i18n";
import MainWrapper from "./src/common/main/MainWrapper.tsx";
import {Route, Routes} from "react-router-dom";
import DashboardPage from "./src/dashboard/DashboardPage.tsx";
import MenuPage from "./src/menu/MenuPage.tsx";
import TableListPage from "./src/table/TableListPage.tsx";
import IngredientListPage from "./src/ingredient/IngredientListPage.tsx";
import DishListPage from "./src/dish/DishListPage.tsx";

function App() {
    const {user} = useContext(AuthContext);

    if (!user) {
        return <LoginPage/>;
    }

    return (
        <MainWrapper>
            <Routes>
                <Route path="/" element={<DashboardPage />} />
                <Route path="/tables" element={<TableListPage />} />
                <Route path="/ingredients" element={<IngredientListPage />} />
                <Route path="/dishes" element={<DishListPage />} />
                <Route path="/menu" element={<MenuPage />} />
            </Routes>
        </MainWrapper>
    );
}

export default App;
