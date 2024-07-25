import React, { ChangeEvent, FormEvent, useEffect, useState } from 'react';
import Container from '../components/Container';
import Controls from '../components/Controls';
import Display from '../components/Display';
import PageName from '../components/PageName';
import '../styling/LoginForm.css';
import Cookies from 'js-cookie';

interface LoginFormProps {
  onLogin: (e: FormEvent, login: string, password: string) => void;
  onRegister: (login: string, password: string) => void;
}

const LoginForm: React.FC<LoginFormProps> = ({ onLogin, onRegister }) => {
  const [activeButton, setActiveButton] = useState('Login');
  const [login, setLogin] = useState('');
  const [password, setPassword] = useState('');
  const [confirmationPassword, setConfirmationPassword] = useState('');
  const [message, setMessage] = useState('');

  const handleTabClick = (button: string) => {
    setActiveButton(button);
    Cookies.set('LoginFormTab', button);
  };

  const onChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    if (name === 'login') setLogin(value);
    else if (name === 'password') setPassword(value);
    else if (name === 'confirmation-password') setConfirmationPassword(value);
  };

  const onSubmitLogin = (e: FormEvent) => {
    
    e.preventDefault();

    onLogin(e, login, password);
    setMessage("Username or password is incorrect.")
  };

  const onSubmitRegister = (e: FormEvent) => {

    e.preventDefault();
    
    if (login.length < 4 || login.length > 16) {
      setMessage("Username must be 4-16 characters.")
    } else if(password.length < 7 || password.length > 16) {
      setMessage("Passwords must be 8-16 characters.")
    } else if (password != confirmationPassword) {
      setMessage("Passwords must match.")
    }  else {
      onRegister(login, password);
      setMessage("Username taken.")
    }
  };

  useEffect(() => {
    const storedTab = Cookies.get('LoginFormTab');
    if (storedTab) {
      setActiveButton(storedTab);
    }
  }, []);

  return (
    <Container>
      <PageName props={"Welcome"} />
      <Display>
        <div className="parent-jawn">
          <div className="child-jawn">
            <div className="tab-jawn">
              {activeButton === 'Login' && (
                <form className="form-jawn" onSubmit={onSubmitLogin}>
                  <div className="username-jawn">
                    <input
                      type="login"
                      value={login}
                      name="login"
                      className="form-control"
                      placeholder="Username"
                      onChange={onChangeHandler}
                    />
                  </div>
                  <div className="password-jawn">
                    <input
                      type="password"
                      value={password}
                      name="password"
                      className="form-control"
                      placeholder="Password"
                      onChange={onChangeHandler}
                    />
                  </div>
                  <div className="login-submit-btn-jawn">
                    <button type="submit" className="login-submit-btn">
                      Sign In
                    </button>
                  </div>
                </form>
              )}

              {activeButton === 'Register' && (
                <form onSubmit={onSubmitRegister}>
                  <div className="username-jawn">
                    <input
                      type="text"
                      value={login}
                      name="login"
                      className="form-control"
                      placeholder="Username"
                      onChange={onChangeHandler}
                    />
                  </div>
                  <div className="password-jawn">
                    <input
                      type="password"
                      value={password}
                      name="password"
                      className="form-control"
                      placeholder="Password"
                      onChange={onChangeHandler}
                    />
                    <input
                      type="password"
                      value={confirmationPassword}
                      name="confirmation-password"
                      className="form-control"
                      placeholder="Confirm Password"
                      onChange={onChangeHandler}
                    />
                    <p style={{color: "red"}}>{message}</p>
                  </div>
                  <div className="login-submit-btn-jawn">
                    <button type="submit" className="login-submit-btn">
                      Register
                    </button>
                  </div>
                </form>
              )}
            </div>
          </div>
          <div className="display-jawn-tabs">
            <button
              className={activeButton === 'Login' ? 'active' : ''}
              onClick={() => handleTabClick('Login')}
            >
              Login
            </button>
            <button
              className={activeButton === 'Register' ? 'active' : ''}
              onClick={() => handleTabClick('Register')}
            >
              Register
            </button>
          </div>
        </div>
      </Display>
      <Controls
        handleClickRightBtnLeft={() => handleTabClick("Login")}
        rightBtnLeftText="ᐊ"
        handleClickRightBtnRight={() => handleTabClick("Register")}
        rightBtnRightText="ᐅ"
      />
    </Container>
  );
};

export default LoginForm;
