import React, { Component } from 'react';
import axios from 'axios';

interface User {
    id: number,
    name: string;
    password: string;
  }
  
  interface State {
    accounts: User[];
    account: User;
  }

class AccountComponent extends Component {
    state: State = {
        accounts: [],
        account: {
            id: 0,
          name: '',
          password: '',
        },
      };

  componentDidMount() {
    this.loadAccounts();
  }

  loadAccounts = () => {
    axios.get('/api/account')
      .then((response) => {
        this.setState({ accounts: response.data });
      })
      .catch((error) => {
        console.error('Error loading accounts:', error);
      });
  };

  getAccountById = (id: number) => {
    axios.get(`/api/account/${id}`)
      .then((response) => {
        const account = response.data;
      })
      .catch((error) => {
        console.error('Error loading account by ID:', error);
      });
  };

  saveAccount = () => {
    const { account } = this.state;

    axios.post('/api/account', account)
      .then(() => {
        this.setState({ account: { username: '', password: ''} });
        this.loadAccounts();
      })
      .catch((error) => {
        console.error('Error saving account:', error);
      });
  };

  deleteAccountById = (id: number) => {
    axios.delete(`/api/account/${id}`)
      .then(() => {
        this.loadAccounts();
      })
      .catch((error) => {
        console.error('Error deleting account by ID:', error);
      });
  };

  render() {

    return (
        <div>
          <button onClick={this.saveAccount}>Save Account</button>
  
          <ul>
            {this.state.accounts.map((account) => (
              <li key={account.id}>
                {account.name} - {account.password}
                <button onClick={() => this.getAccountById(account.id)}>Edit</button>
                <button onClick={() => this.deleteAccountById(account.id)}>Delete</button>
              </li>
            ))}
          </ul>
        </div>
      );
  }
}

export default AccountComponent;