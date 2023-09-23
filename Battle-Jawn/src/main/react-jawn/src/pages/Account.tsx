import React, { ChangeEvent, Component, useState } from 'react';
import axios from 'axios';
import "../styling/Account.css";
import UserPromptText from '../components/UserPromptText';
import { useNavigate } from 'react-router-dom';

function Account() {

  const [username, setUsername] = useState('');
  const navigate = useNavigate();

  const createAccount = () => {
    axios.post('http://localhost:8080/api/account', { username })
      .then((response) => {
        console.log('User created successfully:', response.data);
        navigate("/hero-creation");
      })
      .catch((error) => {
        console.error('Error creating user:', error);
      });
  };

  const handleUsernameChange = (event: ChangeEvent<HTMLInputElement>) => {
    setUsername(event.target.value);
  }
  
    return (
        <div className="container">
          <UserPromptText text="Create An Account" />

        <div className="account-name-container">
          <h3 className="account-name">Account Name:</h3>
          <input type="text"
            placeholder="Enter username"
            value={username}
            onChange={handleUsernameChange}></input>
        </div>

          <button className="submit-button" onClick={createAccount}>Create New Account</button>
        </div>
      );
  }

export default Account;