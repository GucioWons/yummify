import './App.css'
import LoginPage from "./src/auth/LoginPage.tsx";
import {AuthContext} from "./src/auth/AuthContext.tsx";
import {useContext} from "react";

function App() {
  const { user } = useContext(AuthContext);

  if (!user) {
    return <LoginPage />;
  }

  return <div>Witaj!</div>;
}

export default App
