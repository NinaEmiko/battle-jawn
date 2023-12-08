import React, { FC, MouseEvent } from 'react';

interface ButtonsProps {
  login: () => void;
  logout: () => void;
}

const Buttons: FC<ButtonsProps> = (props) => {
  return (
    <div className="row">
      <div className="col-md-12 text-center" style={{ marginTop: '30px' }}>
        <button className="btn btn-primary" style={{ margin: '10px' }} onClick={props.login}>
          Login
        </button>
        <button className="btn btn-dark" style={{ margin: '10px' }} onClick={props.logout}>
          Logout
        </button>
      </div>
    </div>
  );
};

export default Buttons;
