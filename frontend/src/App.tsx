import "./App.css";
import "./styling/Container.css"
import { BrowserRouter, Routes, Route } from "react-router-dom";
import CustomNavBar from "./components/CustomNavBar";
import { FormEvent, useState } from "react";
import LoginForm from "./components/LoginForm";
import { getAuthToken, request, setAuthHeader } from "./helpers/axios_helper";
import MyHeroes from "./components/MyHeroes";
import AccountSettings from "./components/AccountSettings";
import CreateNewHero from "./components/CreateNewHero";
import LeaderBoard from "./components/LeaderBoard";
import Inventory from "./components/Inventory";
import AboutUs from "./components/AboutUs";
import axios from "axios";

function App() {
  const [currentUser, setCurrentUser] = useState({
    userName: '',
    id: 0,
    loggedIn: false,
}) 

  const logout = () => {
    setCurrentUser((prev) => ({
      ...prev,
      loggedIn: false,
    }));
    setAuthHeader(null);
  };

  

  const onLogin = (e: FormEvent, username: string, password: string) => {
    e.preventDefault();
    request('POST', `${import.meta.env.VITE_REACT_APP_URL}/login`, {
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
      request('POST', `${import.meta.env.VITE_REACT_APP_URL}/register`, {

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
      <div className="login-screen-background-jawn">
        <CustomNavBar pageTitle="Battle Jawn" onLogout={logout} isLoggedIn={currentUser.loggedIn}/>
        {/* <div className="background-jawn"> */}
        <Routes>
            {currentUser.loggedIn && (
              <Route key="my-heroes" path="/" element={<MyHeroes props={currentUser} />} />
            )}
            {!currentUser.loggedIn && (
              <Route key="login" path="/" element={<LoginForm onLogin={onLogin} onRegister={onRegister} />} />
            )}
            {currentUser.loggedIn && (
              <Route key="create-hero" path="/create-hero" element={ <CreateNewHero props={currentUser} />} />
            )}
            {currentUser.loggedIn && (
              <Route key="leader-board" path="/leader-board" element={ <LeaderBoard props={currentUser} />} />
            )}
            {currentUser.loggedIn && (
              <Route key="account-settings" path="/account-settings" element={ <AccountSettings props={currentUser} />} />
            )}
            {currentUser.loggedIn && (
              <Route key="inventory" path="/inventory" element={ <Inventory props={currentUser} />} />
            )}
            {currentUser.loggedIn && (
              <Route key="about-us" path="/about-us" element={ <AboutUs />} />
            )}
          </Routes>
        </div>
      {/* </div> */}
    </BrowserRouter>
  )
}

export default App;
