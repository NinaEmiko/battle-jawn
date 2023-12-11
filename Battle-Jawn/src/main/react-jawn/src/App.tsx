import "./App.css";
import "./styling/Container.css"
import { BrowserRouter, Routes, Route, useNavigate } from "react-router-dom";
import CustomNavBar from "./components/CustomNavBar";
import { FormEvent, useState } from "react";
import LoginForm from "./components/LoginForm";
import { request, setAuthHeader } from "./helpers/axios_helper";
import MyHeroes from "./components/MyHeroes";
import BattleContainer from "./components/BattleContainer";
import AccountSettings from "./components/AccountSettings";

function App() {
  const [currentUser, setCurrentUser] = useState({
    userName: '',
    id: 0,
    loggedIn: false,
}) 

console.log("User Account Id(App): " + currentUser.id);

  const logout = () => {
    setCurrentUser((prev) => ({
      ...prev,
      loggedIn: false,
    }));
    setAuthHeader(null);
  };

  const onLogin = (e: FormEvent, username: string, password: string) => {
    e.preventDefault();
    request('POST', 'http://localhost:8080/login', {
      login: username,
      password: password,
    })
      .then((response) => {
        setAuthHeader(response.data.token);
        setCurrentUser(() => ({
          id: response.data.id,
          userName: response.data.userName,
          loggedIn: true,
        }));
      })
      .catch((error) => {
        setAuthHeader(null);
      });
  };

  const onRegister = (event: FormEvent, username: string, password: string) => {
    event.preventDefault();
    request('POST', 'http://localhost:8080/register', {
      login: username,
      password: password,
    })
      .then((response) => {
        setAuthHeader(response.data.token);
        setCurrentUser(() => ({
          id: response.data.id,
          userName: response.data.userName,
          loggedIn: true,
        }));
      })
      .catch((error) => {
        setAuthHeader(null);
      });
  };
  
  return (
    <BrowserRouter>
      <div>
        <CustomNavBar pageTitle="Battle Jawn" onLogout={logout} isLoggedIn={currentUser.loggedIn}/>
        <div className="background-jawn">
        <Routes>
            {currentUser.loggedIn && (
              <Route key="my-heroes" path="/" element={<MyHeroes props={currentUser} />} />
            )}
            {!currentUser.loggedIn && (
              <Route key="login" path="/" element={<LoginForm onLogin={onLogin} onRegister={onRegister} />} />
            )}
            {currentUser.loggedIn && (
              <Route key="battle" path="/battle" element={ <BattleContainer props={currentUser} />} />
            )}
            {currentUser.loggedIn && (
              <Route key="account-settings" path="/account-settings" element={ <AccountSettings />} />
            )}
          </Routes>
        </div>
      </div>
    </BrowserRouter>
  )
}

export default App;
