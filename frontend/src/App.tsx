import { BrowserRouter, Routes, Route } from "react-router-dom";
import CustomNavBar from "./pages/CustomNavBar";
import { FormEvent, useState } from "react";
import LoginForm from "./pages/LoginForm";
import { getAuthToken, request, setAuthHeader } from "./helpers/axios_helper";
import AccountSettings from "./pages/AccountSettings";
import CreateNewHero from "./pages/CreateNewHero";
import LeaderBoard from "./pages/LeaderBoard";
import Inventory from "./pages/Inventory";
import AboutUs from "./pages/AboutUs";
import Home from "./pages/Home";

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
      {/* <div className=""> */}
        <CustomNavBar pageTitle="Battle Jawn" onLogout={logout} isLoggedIn={currentUser.loggedIn}/>
        {/* <div className="background-jawn"> */}
        <Routes>
            {currentUser.loggedIn && (
              <Route key="my-heroes" path="/" element={<Home props={currentUser} />} />
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
              <Route key="account-settings" path="/account-settings" element={ <AccountSettings props={currentUser} logout={logout} />} />
            )}
            {currentUser.loggedIn && (
              <Route key="inventory" path="/inventory" element={ <Inventory props={currentUser} />} />
            )}
            {currentUser.loggedIn && (
              <Route key="about-us" path="/about-us" element={ <AboutUs />} />
            )}
          </Routes>
        {/* </div> */}
      {/* </div> */}
    </BrowserRouter>
  )
}

export default App;
