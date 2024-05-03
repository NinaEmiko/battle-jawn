import { request, setAuthHeader } from "../helpers/axios_helper";
import { useState } from 'react';
import classNames from 'classnames';
import "../styling/AccountSettings.css";
import PopUp from "./PopUp";
import { useNavigate } from "react-router-dom";

const AccountSettings = ({props, logout}:{ props: any, logout: () => void}) => {
  const apiUrl = import.meta.env.VITE_REACT_APP_URL;
  const [newPassword, setNewPassword] = useState('');
  const [message, setMessage] = useState('');
  const [showDeleteAccount, setShowDeleteAccount] = useState(false);
  const [showUpdatePassword, setShowUpdatePassword] = useState(false);
  const [active, setActive] = useState('');
  const [popUpType, setPopUpType] = useState("");
  const [popUpContent, setPopUpContent] = useState("");
  const [showPopUp, setShowPopUp] = useState(false);

  const navigate = useNavigate();

  const handleNavigation = (path: string) => {
    navigate(path);
  };

  const handleDeleteAccount = async () => {
    try {
      const response = await request('DELETE', apiUrl + '/delete/' + props.id, {});
      setAuthHeader(null);
    }
    catch (error) {
      console.error('Error deleting account:', error);
    }
  };

  const handlePasswordChange = async () => {
    try {
      const response = await request('PUT', apiUrl + '/update/${props.id}', {
      newPassword: newPassword,
    });
    setMessage(response.data);
    } catch (error) {
      setMessage('Error updating password.');
    }
  }

  const toggleShowSettings = (setting: string) => {
    if (setting == 'update password') {
      setShowUpdatePassword(!showUpdatePassword);
      setActive(setting)
      setShowDeleteAccount(false);
    } else if (setting == 'delete account') {
      setShowDeleteAccount(!showDeleteAccount);
      setActive(setting)
      setShowUpdatePassword(false);
    }
  }

  function handleDeleteConfirmation(): void {
    setPopUpType("confirmation");
    setPopUpContent("delete account");
    setShowPopUp(true);
  }

  function handleOkButtonClick() {
    setShowPopUp(false);
  }
  
  function handleConfirmButtonClick() {
    setShowPopUp(false);
    handleDeleteAccount();
    logout();
    handleNavigation("/");
  }

  return (
    <div className="account-settings-background-jawn">

        {showPopUp ?
          <PopUp 
            props={{
                type: popUpType,
                content: popUpContent,
                onClickOk: handleOkButtonClick,
                onClickConfirm: handleConfirmButtonClick
            }} 
          />   
        :

    <div className="container-jawn-login-form log-reg">
      <div className="row justify-content-center">
        <h1 className="account-settings-title-jawn">Account Settings</h1>
        <ul className="nav-justified mb-3 log-reg-2" id="ex1" role="tablist">
          <li className="nav-item" role="presentation">
            <button
            className={classNames('nav-link', 'tab-update-password', 'btn', 'custom-button', active === 'update password' ? 'active' : '')}
            id="tab-login"
            onClick={(e) => toggleShowSettings('update password')}
            >
              Update Password
            </button>
          </li>
          <li className="nav-item" role="presentation">
            <button
            className={classNames('nav-link', 'tab-delete-account', 'btn', 'custom-button', active === 'delete account' ? 'active' : '')}
            id="tab-register"
            onClick={(e) => toggleShowSettings('delete account')}
            >
              Delete Account
            </button>
          </li>

        </ul>
            {showUpdatePassword && (
              <div>
                  <div className="userPrompt">
                      {"New Password: "}
                  </div>
                  <input className="hero-name-input" type="password" value={newPassword} onChange={(e) => setNewPassword(e.target.value)}></input>
                  <button onClick={handlePasswordChange} className="btn" id="submit-btn">Submit</button>
                  <p>{message}</p>
              </div>
            )}

            {showDeleteAccount && (
              <div>
                <button onClick={handleDeleteConfirmation} className="btn" id="delete-btn">Delete</button>
              </div>
            )}
      </div>
    </div>
}
    </div>
  );
};

export default AccountSettings;
