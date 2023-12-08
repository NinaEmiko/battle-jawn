import React, { ChangeEvent, FormEvent } from 'react';
import classNames from 'classnames';
import "../App.css";

interface LoginFormProps {
  onLogin: (e: FormEvent, login: string, password: string) => void;
  onRegister: (
    e: FormEvent,
    login: string,
    password: string
  ) => void;
}

interface LoginFormState {
  active: string;
  login: string;
  password: string;
}

export default class LoginForm extends React.Component<
  LoginFormProps,
  LoginFormState
> {
  constructor(props: LoginFormProps) {
    super(props);
    this.state = {
      active: 'login',
      login: '',
      password: '',
    };
  }

  onChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    const name = event.target.name;
    const value = event.target.value;
    this.setState({ [name]: value } as Pick<LoginFormState, keyof LoginFormState>);
  };

  onSubmitLogin = (e: FormEvent) => {
    this.props.onLogin(e, this.state.login, this.state.password);
  };

  onSubmitRegister = (e: FormEvent) => {
    this.props.onRegister(
      e,
      this.state.login,
      this.state.password
    );
  };

  render() {
    return (
        <div className="container-jawn">
      <div className="row justify-content-center">
        <div className="col-4">
          <ul className="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
            <li className="nav-item" role="presentation">
              <button
                className={classNames('nav-link', this.state.active === 'login' ? 'active' : '')}
                id="tab-login"
                onClick={() => this.setState({ active: 'login' })}
              >
                Login
              </button>
            </li>
            <li className="nav-item" role="presentation">
              <button
                className={classNames('nav-link', this.state.active === 'register' ? 'active' : '')}
                id="tab-register"
                onClick={() => this.setState({ active: 'register' })}
              >
                Register
              </button>
            </li>
          </ul>

          <div className="tab-content">
            <div
              className={classNames('tab-pane', 'fade', this.state.active === 'login' ? 'show active' : '')}
              id="pills-login"
            >
              <form onSubmit={this.onSubmitLogin}>
                <div className="form-outline mb-4">
                  <input
                    type="login"
                    id="loginName"
                    name="login"
                    className="form-control"
                    onChange={this.onChangeHandler}
                  />
                  <label className="form-label" htmlFor="loginName">
                    Username
                  </label>
                </div>

                <div className="form-outline mb-4">
                  <input
                    type="password"
                    id="loginPassword"
                    name="password"
                    className="form-control"
                    onChange={this.onChangeHandler}
                  />
                  <label className="form-label" htmlFor="loginPassword">
                    Password
                  </label>
                </div>

                <button type="submit" className="btn">
                  Sign in
                </button>
              </form>
            </div>

            <div
              className={classNames('tab-pane', 'fade', this.state.active === 'register' ? 'show active' : '')}
              id="pills-register"
            >
              <form onSubmit={this.onSubmitRegister}>

                <div className="form-outline mb-4">
                  <input
                    type="text"
                    id="login"
                    name="login"
                    className="form-control"
                    onChange={this.onChangeHandler}
                  />
                  <label className="form-label" htmlFor="login">
                    Username
                  </label>
                </div>

                <div className="form-outline mb-4">
                  <input
                    type="password"
                    id="registerPassword"
                    name="password"
                    className="form-control"
                    onChange={this.onChangeHandler}
                  />
                  <label className="form-label" htmlFor="registerPassword">
                    Password
                  </label>
                </div>

                <button type="submit" className="btn">
                  Sign in
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
      </div>
    );
  }
}
