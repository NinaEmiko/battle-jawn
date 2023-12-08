import React, { FC, MouseEvent } from 'react';

interface ButtonsProps {
  loggedIn: boolean;
  login: () => void;
  logout: () => void;
}

const Buttons: React.FC<ButtonsProps> = ({ loggedIn, login, logout }) => {
  return (
    <div>
      {loggedIn ? (
        <button onClick={logout}>Logout</button>
      ) : (
        <button onClick={login}>Login</button>
      )}
    </div>
  );
};

export default Buttons;
