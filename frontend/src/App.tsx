import { FormEvent, useEffect, useState } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginForm from './pages/LoginForm';
import AccountSettings from './pages/AccountSettings';
import LeaderBoard from './pages/LeaderBoard';
import AboutUs from './pages/AboutUs';
import Home from './pages/Home';
import HowTo from './pages/HowTo';
import { request, setAuthHeader } from './helpers/axios_helper';
import Cookies from 'js-cookie';
import NavJawn from './components/NavJawn';

function App() {
  const [isExpanded, setIsExpanded] = useState<boolean>(false);
  const [currentUser, setCurrentUser] = useState({
    userName: '',
    id: 0,
    loggedIn: false,
  });

  const toggleNav = () => {
    setIsExpanded(!isExpanded);
  };

  const logout = () => {
    toggleNav();
    setCurrentUser((prev) => ({
      ...prev,
      loggedIn: false,
    }));
    setAuthHeader(null);
    Cookies.set('storedId', "");
    Cookies.set('storedUserName', "");
    Cookies.set('authHeader', "");
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
        Cookies.set('authHeader', response.data.token);
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
        Cookies.set('authHeader', response.data.token);
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
    const auth = Cookies.get('authHeader');
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
      <div className="dropdown-container">
        {isExpanded && <NavJawn props={{logout:logout, toggleNav: toggleNav}}/>}
      </div>
      <Routes>
        {!currentUser.loggedIn && <Route key="login" path="/" element={<LoginForm onLogin={onLogin} onRegister={onRegister} currentUser={currentUser} logout={(logout)} />} />}
        {currentUser.loggedIn && <Route key="my-heroes" path="/" element={<Home props={{currentUser: currentUser, toggleNav:toggleNav }} />} />}
        {currentUser.loggedIn && <Route key="leader-board" path="/leader-board" element={<LeaderBoard props={{currentUser: currentUser, toggleNav:toggleNav }} />} />}
        {currentUser.loggedIn && <Route key="account-settings" path="/account-settings" element={<AccountSettings props={{currentUser: currentUser, toggleNav:toggleNav }} />} />}
        {<Route key="about-us" path="/about-us" element={<AboutUs props={{currentUser: currentUser, toggleNav:toggleNav }} />} />}
        {<Route key="how-to" path="/how-to" element={<HowTo props={{currentUser: currentUser, toggleNav:toggleNav }} />} />}
      </Routes>
    </BrowserRouter>
  );
}

export default App;
