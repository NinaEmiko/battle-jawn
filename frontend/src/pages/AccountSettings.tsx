import { useState } from "react";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";
import { request, setAuthHeader } from "../helpers/axios_helper";
import '../styling/AccountSettings.css';
import PopUp from "../components/PopUp";
import { useNavigate } from "react-router-dom";

const AccountSettings = ({props, logout}:{ props: any, logout: () => void}) => {
    const apiUrl = import.meta.env.VITE_REACT_APP_URL;
    const [activeButton, setActiveButton] = useState("Update Password");
    const [newPassword, setNewPassword] = useState('');
    const [oldPassword, setOldPassword] = useState('');
    const [confirmNewPassword, setConfirmNewPassword] = useState('');
    const [message, setMessage] = useState('');
    const [popUpType, setPopUpType] = useState("");
    const [popUpContent, setPopUpContent] = useState("");
    const [showPopUp, setShowPopUp] = useState(false);

    const handleTabClick = (button: string) => {
        setMessage("");
        setActiveButton(button);
      };

      const navigate = useNavigate();

      const handleNavigation = (path: string) => {
          navigate(path);
      };

    const handlePasswordChange = async () => {
        if (newPassword === confirmNewPassword) {
            try {
                const response = await request('PUT', apiUrl + '/update/' + props.id, {
                newPassword: newPassword,
                oldPassword: oldPassword,
              });
              setMessage(response.data);
              } catch (error) {
                setMessage('Error updating password.');
              }
        } else {
            setMessage('Passwords must match.')
        }
    }

    const handleDeleteAccount = async () => {
        try {
          const response = await request('DELETE', apiUrl + '/delete/' + props.id, {});
          setAuthHeader(null);
        }
        catch (error) {
          console.error('Error deleting account:', error);
        }
    };

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
      }

    return (        
        <Container>
            <PageName props={"Account Settings"} />

            <Display>
                <>
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
                        <>
                            <div className="parent-jawn">
                                <div className="child-jawn">
                            {activeButton === "Update Password" && (

                                <div className="account-settings-container-jawn">
                                        <input type="password"
                                        className="new-password-input"
                                        placeholder="Old Password"
                                        value={oldPassword} onChange={(e) => setOldPassword(e.target.value)} />
                                        <input type="password"
                                        className="new-password-input"
                                        placeholder="New Password"
                                        value={newPassword} onChange={(e) => setNewPassword(e.target.value)} />
                                        <input type="password"
                                        className="new-password-input"
                                        placeholder="Confirm New Password"
                                        value={confirmNewPassword} onChange={(e) => setConfirmNewPassword(e.target.value)} />
                                        <button className="new-password-submit-btn" onClick={handlePasswordChange}>Submit</button>
                                        <p className="new-password-msg">{message}</p>
                                </div>
                            )}

                            {activeButton === "Delete Account" && (
                                <div className="account-settings-container-jawn">
                                    <div className="delete-account-txt">
                                        WARNING: You are about to delete your account. This action cannot be undone. Are you sure?
                                    </div>
                                    <div className="delete-account-jawn">
                                        <button className="delete-account-btn" onClick={handleDeleteConfirmation}>Delete</button>
                                        <p>{message}</p>
                                    </div>
                                </div>
                            )}

                            </div>
                            </div>

                            <div className="display-jawn-tabs">
                                <button className={activeButton === 'Update Password' ? 'active' : ''} onClick={()=> handleTabClick("Update Password")}>Update Password</button>
                                <button className={activeButton === 'Delete Account' ? 'active' : ''} onClick={()=> handleTabClick("Delete Account")}>Delete Account</button>
                            </div>
                        </>
                    }
                </>
            </Display>
            <Controls
                handleClickLeftBtnBottom={() => handleNavigation("/")}
                leftBtnBottomText="Exit"
                handleClickRightBtnLeft={() => handleTabClick("Update Password")}
                rightBtnLeftText="Left"
                handleClickRightBtnRight={() => handleTabClick("Delete Account")}
                rightBtnRightText="Right"
            />
        </Container>
    )
}

export default AccountSettings;