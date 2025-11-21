import './App.css'
import LoginPage from "./src/auth/page/LoginPage.tsx";
import {AuthContext} from "./src/auth/context/AuthContext.tsx";
import {useContext} from "react";

function App() {
  const { user, logout } = useContext(AuthContext);

  if (!user) {
    return <LoginPage />;
  }

  return <>
      <div>Witaj!</div>;
      <button onClick={logout}>Logout</button>
    </>
}

export default App
