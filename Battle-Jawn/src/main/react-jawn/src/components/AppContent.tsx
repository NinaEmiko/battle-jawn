import { Component, FormEvent } from 'react';
import { request, setAuthHeader } from '../helpers/axios_helper';
import Buttons from './Buttons';
import HomePage from '../pages/HomePage';
import LoginForm from './LoginForm';
import WelcomeContent from './WelcomeContent';

interface AppContentState {
  componentToShow: string;
}

export default class AppContent extends Component<{}, AppContentState> {
  constructor(props: {}) {
    super(props);
    this.state = {
      componentToShow: 'welcome',
    };
  }

  login = () => {
    this.setState({ componentToShow: 'login' });
  };

  logout = () => {
    this.setState({ componentToShow: 'welcome' });
    setAuthHeader(null);
  };

  onLogin = (e: FormEvent, username: string, password: string) => {
    e.preventDefault();
    request('POST', 'http://localhost:8080/login', {
      login: username,
      password: password,
    })
      .then((response) => {
        setAuthHeader(response.data.token);
        this.setState({ componentToShow: 'player-selection' });
      })
      .catch((error) => {
        setAuthHeader(null);
        this.setState({ componentToShow: 'welcome' });
      });
  };

  onRegister = (event: FormEvent, username: string, password: string) => {
    event.preventDefault();
    request('POST', 'http://localhost:8080/register', {
      login: username,
      password: password,
    })
      .then((response) => {
        setAuthHeader(response.data.token);
        this.setState({ componentToShow: 'player-selection' });
      })
      .catch((error) => {
        setAuthHeader(null);
        this.setState({ componentToShow: 'welcome' });
      });
  };

  render() {
    return (
      <>
        <Buttons login={this.login} logout={this.logout} />

        {this.state.componentToShow === 'welcome' && <WelcomeContent />}
        {this.state.componentToShow === 'login' && <LoginForm onLogin={this.onLogin} onRegister={this.onRegister} />}
        {this.state.componentToShow === 'player-selection' && <HomePage />}
      </>
    );
  }
}
