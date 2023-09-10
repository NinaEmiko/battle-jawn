import React, { ChangeEvent, Component, useState } from 'react';
import axios from 'axios';

function Account() {

  const [username, setUsername] = useState('');

  const createAccount = () => {
    axios.post('http://localhost:8080/api/account', { username })
      .then((response) => {
        console.log('User created successfully:', response.data);
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
          <p>Username: </p>
          <input type="text"
            placeholder="Enter username"
            value={username}
            onChange={handleUsernameChange}></input>
          <button onClick={createAccount}>Create New Account</button>
        </div>
      );
  }

export default Account;