import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import CustomNavBar from "./components/CustomNavBar";
import { FormEvent, useState } from "react";
import LoginForm from "./components/LoginForm";
import HomePage from "./pages/HomePage";
import { request, setAuthHeader } from "./helpers/axios_helper";

function App() {
  const [loggedIn, setLoggedIn] = useState(false);

  const logout = () => {
    setLoggedIn(false);
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
        setLoggedIn(true);
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
        setLoggedIn(true);
      })
      .catch((error) => {
        setAuthHeader(null);
      });
  };
  
  return (
    <BrowserRouter>
      <div>
        <CustomNavBar pageTitle="Battle Jawn" onLogout={logout}/>
        <div className="background-jawn">
        <Routes>
            <Route
              path="/"
              element={
                loggedIn ? (
                  <HomePage />
                ) : (
                  <LoginForm onLogin={onLogin} onRegister={onRegister} />
                )
              }
            />
          </Routes>
        </div>
      </div>
    </BrowserRouter>
  )
}

export default App;
