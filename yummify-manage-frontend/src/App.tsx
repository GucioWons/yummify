import './App.css'
import LoginPage from "./src/auth/page/LoginPage.tsx";
import {AuthContext} from "./src/auth/context/AuthContext.tsx";
import {useContext} from "react";
import "./src/common/i18n/i18n";
import {useTranslation} from "react-i18next";
import {RestaurantContext} from "./src/restaurant/context/RestaurantContext.tsx";

function App() {
  const { user, logout } = useContext(AuthContext);
  const { restaurant } = useContext(RestaurantContext);

  const { t } = useTranslation();

  if (!user) {
    return <LoginPage />;
  }

  return <>
      <div>{t("welcome", { name: user.username })}</div>;
      <div>{restaurant?.name}</div>;
      <button onClick={logout}>{t("logout")}</button>
    </>
}

export default App;
