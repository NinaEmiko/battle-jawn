import { request, setAuthHeader } from "../helpers/axios_helper";
import { useState } from 'react';

const AccountSettings = ({props}:{props:any}) => {
  const [accountIsDeleted, setAccountIsDeleted] = useState(false);
  const [newPassword, setNewPassword] = useState('');
  const [message, setMessage] = useState('');

  const onDelete = async () => {
    try {
      const response = await request('DELETE', `http://localhost:8080/delete/${props.id}`, {});
        setAccountIsDeleted(true);
      setAuthHeader(null);
    }
    catch (error) {
      console.error('Error deleting account:', error);
    }
  };

  const handlePasswordChange = async () => {
    try {
      const response = await request('PUT', `http://localhost:8080/update/${props.id}`, {
      newPassword: newPassword,
    });
    setMessage(response.data);
    } catch (error) {
      setMessage('Error updating password.');
    }
  }

  return (
    <div className="container-jawn-login-form">
        <h1 className="title-jawn">Account Settings</h1>
            <label className="account-settings-jawn">New Password</label>
            <input type="password" value={newPassword} onChange={(e) => setNewPassword(e.target.value)}></input>
            <button onClick={handlePasswordChange}>Update Password</button>
              <p>{message}</p>
            <button onClick={() => onDelete()} className="account-settings-jawn">Delete Account</button>
            {accountIsDeleted && (
              <p>Account successfully deleted</p>
            )}
    </div>
  );
};

export default AccountSettings;
