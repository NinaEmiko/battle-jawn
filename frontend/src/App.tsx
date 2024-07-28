import { FormEvent, useEffect, useState } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import CustomNavBar from './components/CustomNavBar';
import LoginForm from './pages/LoginForm';
import AccountSettings from './pages/AccountSettings';
import LeaderBoard from './pages/LeaderBoard';
import AboutUs from './pages/AboutUs';
import Home from './pages/Home';
import HowTo from './pages/HowTo';
import { request, setAuthHeader } from './helpers/axios_helper';
import Cookies from 'js-cookie';

function App() {
  const [currentUser, setCurrentUser] = useState({
    userName: '',
    id: 0,
    loggedIn: false,
  });

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
        Cookies.set('storedId', response.data.id);
        Cookies.set('storedUserName', response.data.userName);
        setAuthHeader(response.data.token);
        setCurrentUser({
          id: response.data.id,
          userName: response.data.userName,
          loggedIn: true,
        });
      })
      .catch((error) => {
        setAuthHeader(null);
      });
  };

  const onRegister = (username: string, password: string) => {
    request('POST', `${import.meta.env.VITE_REACT_APP_URL}/register`, {
      login: username,
      password: password,
    })
      .then((response) => {
        Cookies.set('storedId', response.data.id);
        Cookies.set('storedUserName', response.data.userName);
        setAuthHeader(response.data.token);
        setCurrentUser({
          id: response.data.id,
          userName: response.data.userName,
          loggedIn: true,
        });
      })
      .catch((error) => {
        setAuthHeader(null);
      });
  };

  useEffect(() => {
    const auth = Cookies.get('AuthHeader');
    const storedId = Cookies.get('storedId');
    const storedUserName = Cookies.get('storedUserName');
    if (storedId && storedUserName && auth) {
      setAuthHeader(auth);
      setCurrentUser({
        id: Number(storedId),
        userName: storedUserName,
        loggedIn: true,
      });
    }
  }, []);

  return (
    <BrowserRouter>
      <CustomNavBar pageTitle="Battle Jawn" onLogout={logout} isLoggedIn={currentUser.loggedIn} />
      <Routes>
        {!currentUser.loggedIn && <Route key="login" path="/" element={<LoginForm onLogin={onLogin} onRegister={onRegister} />} />}
        {currentUser.loggedIn && <Route key="my-heroes" path="/" element={<Home props={currentUser} />} />}
        {currentUser.loggedIn && <Route key="leader-board" path="/leader-board" element={<LeaderBoard />} />}
        {currentUser.loggedIn && <Route key="account-settings" path="/account-settings" element={<AccountSettings props={currentUser} logout={logout} />} />}
        {<Route key="about-us" path="/about-us" element={<AboutUs />} />}
        {<Route key="how-to" path="/how-to" element={<HowTo />} />}
      </Routes>
    </BrowserRouter>
  );
}

export default App;
