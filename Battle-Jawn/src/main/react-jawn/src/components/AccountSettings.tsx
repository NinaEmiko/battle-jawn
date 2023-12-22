import { request, setAuthHeader } from "../helpers/axios_helper";
import { useEffect, useState } from 'react';
import { PropsFromToggle } from 'react-bootstrap/esm/DropdownToggle';

const AccountSettings = ({props}:{props:any}) => {
  const [accountIsDeleted, setAccountIsDeleted] = useState(false);

  const onDelete = () => {
    request('DELETE', 'http://localhost:8080/delete/' + props.id, {})
      .then((response: any) => {
        setAccountIsDeleted(true);
        
      })
      .catch((error: any) => {
        setAuthHeader(null);
      });
  };

  return (
    <div className="container-jawn-login-form">
        <h1 className="title-jawn">Account Settings</h1>
            <p className="account-settings-jawn">Edit Password</p>
            <button onClick={() => onDelete()} className="account-settings-jawn">Delete Account</button>
            {accountIsDeleted && (
              <p>Account successfully deleted</p>
            )}
    </div>
  );
};

export default AccountSettings;
