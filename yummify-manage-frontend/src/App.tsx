import './App.css'
import LoginPage from "./src/auth/LoginPage.tsx";
import {AuthContext} from "./src/auth/AuthContext.tsx";
import {useContext} from "react";

function App() {
  const { user, logout } = useContext(AuthContext);

  if (!user) {
    return <LoginPage />;
  }

  console.log(user);
  return <>
      <div>Witaj!</div>;
      <button onClick={logout}>Logout</button>
    </>
}

export default App
