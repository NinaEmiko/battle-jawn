import { request, setAuthHeader } from "../helpers/axios_helper";
import { useState } from 'react';
import classNames from 'classnames';

const AccountSettings = ({props}:{props:any}) => {
  const [newPassword, setNewPassword] = useState('');
  const [message, setMessage] = useState('');
  const [showDeleteAccount, setShowDeleteAccount] = useState(false);
  const [showUpdatePassword, setShowUpdatePassword] = useState(false);
  const [active, setActive] = useState('')

  const onSubmitDelete = async () => {
    try {
      const response = await request('DELETE', "http://${REACT_APP_API_URL}:${PORT}/delete/${props.id}", {});
      setAuthHeader(null);
    }
    catch (error) {
      console.error('Error deleting account:', error);
    }
  };

  const handlePasswordChange = async () => {
    try {
      const response = await request('PUT', "http://${REACT_APP_API_URL}:${PORT}/update/${props.id}", {
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

  return (
    <div className="account-settings-background-jawn">
    <div className="container-jawn-login-form log-reg">
      <div className="row justify-content-center">
        <h1 className="title-jawn">Account Settings</h1>
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
                <button onClick={() => onSubmitDelete()} className="btn" id="submit-btn">Submit</button>
              </div>
            )}
      </div>
    </div>
    </div>
  );
};

export default AccountSettings;
