import { useState } from "react";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";
import { request, setAuthHeader } from "../helpers/axios_helper";
import '../styling/AccountSettings.css';
import { useNavigate } from "react-router-dom";

const AccountSettings = ({props, logout}:{ props: any, logout: () => void}) => {
    const apiUrl = import.meta.env.VITE_REACT_APP_URL;
    const [activeTab, setActiveTab] = useState("Update Password");
    const [newPassword, setNewPassword] = useState('');
    const [oldPassword, setOldPassword] = useState('');
    const [confirmNewPassword, setConfirmNewPassword] = useState('');
    const [message, setMessage] = useState('');
    const [centerButtonText, setCenterButtonText] = useState("Submit");
    const [exitButtonText, setExitButtonText] = useState("Exit");
    const [leftButtonText, setLeftButtonText] = useState("Left");
    const [rightButtonText, setRightButtonText] = useState("Right");

    const navigate = useNavigate();

    const handleNavigation = (path: string) => {
        navigate(path);
    };

    //API CALLS

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

    //BUTTON HANDLERS

    const handleTabClick = (tab: string) => {
        if (activeTab === "Delete Confirmation") {
            setLeftButtonText("Left")
            setRightButtonText("Right")
            setCenterButtonText("Submit")
            setExitButtonText("Exit")
        }
        setMessage("");
        setActiveTab(tab);

    };
    const handleClickDirectionButton = () => {
        if (activeTab === "Update Password"){
            setActiveTab("Delete Account");
        } else if (activeTab === "Delete Account"){
            setActiveTab("Update Password");
        }
    }

    const handleClickSubmit = () => {
        if (activeTab === "Update Password") {
            handlePasswordChange()
        } else if (activeTab === "Delete Confirmation"){
            handleDeleteAccount();
            logout();
        } else if (activeTab === "Delete Account"){
            setLeftButtonText("")
            setRightButtonText("")
            setCenterButtonText("Delete")
            setExitButtonText("Decline")
            setActiveTab("Delete Confirmation")
        }
    }
    
    function handleExitButtonClick() {
        if (exitButtonText === "Exit"){
            handleNavigation("/")
        } else if (exitButtonText === "Decline"){
            setActiveTab("Delete Account")
            setLeftButtonText("Left")
            setRightButtonText("Right")
            setCenterButtonText("Submit")
            setExitButtonText("Exit")
        }
    }

    return (        
        <Container>
            <PageName props={"Account Settings"} />

            <Display>
                <>  
                    <div className="parent-jawn">
                        <div className="child-jawn">
                            {activeTab === "Delete Confirmation" &&     
                                <div className="account-settings-container-jawn">
                                    <div className="delete-account-txt">
                                        WARNING: You are about to delete your account. This action cannot be undone. Are you sure?                                    </div>
                                    </div>
                            }

                            {activeTab === "Update Password" && (
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
                                        <p className="new-password-msg">{message}</p>
                                </div>
                            )}

                            {activeTab === "Delete Account" && (
                                <div className="account-settings-container-jawn">
                                    <div className="delete-account-txt">
                                        <p>Do you wish to delete your account?</p>
                                    </div>
                                    <div className="delete-account-jawn">
                                        <p>{message}</p>
                                    </div>
                                </div>
                            )}
                        </div>
                    </div>

                    <div className="display-jawn-tabs">
                        <button className={activeTab === 'Update Password' ? 'active' : ''} onClick={()=> handleTabClick("Update Password")}>Update Password</button>
                        <button className={activeTab === 'Delete Account' ? 'active' : ''} onClick={()=> handleTabClick("Delete Account")}>Delete Account</button>
                    </div>
                </>
            </Display>
            <Controls
                handleClickLeftBtnBottom={() => handleExitButtonClick()}
                leftBtnBottomText={exitButtonText}
                handleClickRightBtnLeft={() => handleClickDirectionButton()}
                rightBtnLeftText={leftButtonText}
                handleClickRightBtnCenter={() => handleClickSubmit()}
                rightBtnCenterText={centerButtonText}
                handleClickRightBtnRight={() => handleClickDirectionButton()}
                rightBtnRightText={rightButtonText}
            />
        </Container>
    )
}

export default AccountSettings;