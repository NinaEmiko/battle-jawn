import React, { ChangeEvent, FormEvent, useEffect, useState } from 'react';
import Container from '../components/Container';
import Controls from '../components/Controls';
import Display from '../components/Display';
import PageName from '../components/PageName';
import '../styling/LoginForm.css'
import Cookies from 'js-cookie';

interface LoginFormProps {
  onLogin: (e: FormEvent, login: string, password: string) => void;
  onRegister: (login: string, password: string) => void;
}

const LoginForm: React.FC<LoginFormProps> = ({ onLogin, onRegister }) => {
  const [activeButton, setActiveButton] = useState('Login');
  const [login, setLogin] = useState('');
  const [password, setPassword] = useState('');
  const [confirmationPassword, setConfirmationPassword] = useState("");
  const [message, setMessage] = useState("");

  const handleTabClick = (button: string) => {
      setActiveButton(button);
      Cookies.set("LoginFormTab", button)
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
  };

  const onSubmitRegister = () => {

    if (password === confirmationPassword){
      onRegister(login, password);  
    } else {
      setMessage("Passwords must match.");
    }
  };

  useEffect(()=>{
    const storedTab = Cookies.get("LoginFormTab");
    if (storedTab) {
      setActiveButton(storedTab);
    }
  })

    return (
      <>
        <Container>
          <PageName>
            <div className="page-name-column-1">
              {/* <button className="page-name-btn">Sign Out</button> */}
            </div>
            <div className="page-name-column-2">
              <div className="page-name-txt">
              Welcome
              </div>
            </div>
            <div className="page-name-column-3">
              {/* <button className="page-name-btn">New Hero</button> */}
            </div>
          </PageName>
          <Display>
            <div className="parent-jawn">
              <div className="child-jawn">

              <div className="tab-jawn">
                {activeButton === 'Login' &&
                  <form 
                    className="form-jawn"
                    onSubmit={onSubmitLogin}
                  >
                    <div className="username-jawn">
                      <input
                        type="login"
                        value={login}
                        name="login"
                        className="form-control"
                        placeholder='Username'
                        onChange={onChangeHandler}
                      /> 
                    </div>

                    <div className="password-jawn">
                      <input
                        type="password"
                        value={password}
                        name="password"
                        className="form-control"
                        placeholder='Password'
                        onChange={onChangeHandler}
                      />
                    </div>

                    
                    <div className="login-submit-btn-jawn">
                      <button type="submit" className="login-submit-btn">
                        Sign In
                      </button>
                    </div>
                  </form>
                }
              

                {activeButton === 'Register' &&
                  <form 
                    onSubmit={onSubmitRegister}
                  >
                    <div className="username-jawn">
                      
                      <input
                        type="text"
                        value={login}
                        name="login"
                        className="form-control"
                        placeholder='Username'
                        onChange={onChangeHandler}
                      />
                    </div>

                    <div className="password-jawn">
                      
                      <input
                        type="password"
                        value={password}
                        name="password"
                        className="form-control"
                        placeholder='Password'
                        onChange={onChangeHandler}
                      />
                      <input
                        type="password"
                        value={confirmationPassword}
                        name="confirmation-password"
                        className="form-control"
                        placeholder='Confirm Password'
                        onChange={onChangeHandler}
                      />
                      <p>{message}</p>

                    </div>
                    <div className="login-submit-btn-jawn">
                      <button onClick={() => onSubmitRegister()} type="submit" className="login-submit-btn">
                        Register
                      </button>
                    </div>
                  </form>
                }
              </div>
              </div>
              <div className="display-jawn-tabs">
                <button className={activeButton === 'Login' ? 'active' : ''} onClick={()=> handleTabClick("Login")}>Login</button>
                <button className={activeButton === 'Register' ? 'active' : ''} onClick={()=> handleTabClick("Register")}>Register</button>
              </div>
            </div>
          </Display>
          <Controls>
            <>
              <div className="controls-left">
                <button className="controls-btn"></button>
                <button className="controls-btn"></button>
                <button className="controls-btn"></button>                    
              </div>
              <div className="controls-right">
                <button className="controls-btn"></button>
                <button className="controls-btn" onClick={()=> handleTabClick("Login")}>Left</button>
                <button className="controls-btn"></button>
                <button className="controls-btn" onClick={()=> handleTabClick("Register")}>Right</button>
                <button className="controls-btn"></button>
              </div>
            </>
          </Controls>
      </Container>
    </>
  );
}

export default LoginForm;