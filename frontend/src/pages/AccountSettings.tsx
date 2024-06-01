import { useEffect, useState } from "react";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";
import { request, setAuthHeader } from "../helpers/axios_helper";
import '../styling/AccountSettings.css';
import PopUp from "../pages/PopUp";

const AccountSettings = ({props, logout}:{ props: any, logout: () => void}) => {
    const apiUrl = import.meta.env.VITE_REACT_APP_URL;
    const [activeButton, setActiveButton] = useState("Update Password");
    const [newPassword, setNewPassword] = useState('');
    const [message, setMessage] = useState('');
    const [popUpType, setPopUpType] = useState("");
    const [popUpContent, setPopUpContent] = useState("");
    const [showPopUp, setShowPopUp] = useState(false);

    const handleTabClick = (button: string) => {
        setMessage("");
        setActiveButton(button);
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
            <PageName>
                <div className="page-name-column-1">
                    {/* <button className="page-name-btn">Sign Out</button> */}
                </div>
                <div className="page-name-column-2">
                    <div className="page-name-txt">Account Settings</div>
                </div>
                <div className="page-name-column-3">
                    {/* <button className="page-name-btn">New Hero</button> */}
                </div>
            </PageName>
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
                                        placeholder="New Password"
                                        value={newPassword} onChange={(e) => setNewPassword(e.target.value)} />
                                        <button className="new-password-submit-btn" onClick={handlePasswordChange}>Submit</button>
                                        <p className="new-password-msg">{message}</p>
                                </div>
                            )}

                            {activeButton === "Delete Account" && (
                                <div className="account-settings-container-jawn">
                                    <div className="delete-account-txt">
                                        WARNING: This action cannot be undone.
                                    </div>
                                    <div className="delete-account-jawn">
                                        <button className="delete-account-btn" onClick={handleDeleteConfirmation}>Delete Account</button>
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
            <Controls>
                <>
                    <div className="controls-left">
                        <button className="controls-btn"></button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn"></button>                    
                    </div>
                    <div className="controls-right">
                        <button className="controls-btn"></button>
                        <button className="controls-btn" onClick={()=> handleTabClick("Update Password")}>Left</button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn" onClick={()=> handleTabClick("Delete Account")}>Right</button>
                        <button className="controls-btn"></button>
                    </div>
                </>
            </Controls>
        </Container>
    )
}

export default AccountSettings;