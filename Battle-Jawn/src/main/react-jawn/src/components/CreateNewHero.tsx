import { useState } from "react";
import { useNavigate } from "react-router-dom";
import PlayerTips from "./PlayerTips";
import { request, setAuthHeader } from "../helpers/axios_helper";
import wizard from "../assets/wizard.png";
import ninja from "../assets/ninja.png";
import athena from "../assets/athena.png";
import antibiotics from "../assets/antibiotics.png";
import "../styling/CreateNewHero.css";
  
function CreateNewHero({props}:{props:any}): React.ReactNode {
    const [heroName, setHeroName] = useState('');
    const [heroRole, setHeroRole] = useState('');
    const [heroRoleMessage, setHeroRoleMessage] = useState('');
    const navigate = useNavigate();

    const handleNavigation = (path: string) => {
        navigate(path);
      };

    const handleFormSubmit = async () => {

        if (!heroName || !heroRole) {
            alert('Must enter a hero name and select a class to continue');
        } else {
            try {
                const response = await request('POST', `http://localhost:8080/api/hero`, {
                    userAccountId: props.id,
                    heroName: heroName,
                    role: heroRole
                });
          
                console.log('Hero created successfully:', response.data);
                handleNavigation("/");
            } catch (error) {
                console.error('Error creating hero:', error);
            }
        }
    }

    const handleHeroRoleSelection = (selectedHeroRole: string) => {
        if (selectedHeroRole == 'Tank') {
            setHeroRole('Tank');
            setHeroRoleMessage("You have chosen to be a Tank")
        } else if (selectedHeroRole == 'Healer') {
            setHeroRole('Healer');
            setHeroRoleMessage("You have chosen to be a Healer")
        } else if (selectedHeroRole == 'DPS') {
            setHeroRole('DPS');
            setHeroRoleMessage("You have chosen to be a DPS")
        } else if (selectedHeroRole == 'Caster') {
            setHeroRole('Caster');
            setHeroRoleMessage("You have chosen to be a Caster")
        } else {
            setHeroRoleMessage('Invalid role');
        }
    }
console.log("Hero name: " + heroName);
    return (
        <div className="create-hero-background-jawn">
            <div className="container-jawn-new-hero">
                <PlayerTips />
                <div className="user-prompt-wrapper-create">
                    <div className="userPrompt">
                        {"Hero Name: "}
                    </div>
                    <div className="hero-name-input">
                        <input className="create-new-hero-name-text"
                        type="text"
                        value={heroName}
                        onChange={(e) => setHeroName(e.target.value)}
                        />
                    </div>
                    <div className="userPrompt">
                        {"Choose a class: "}
                    </div>
                    {heroRoleMessage && (
                        <p>{heroRoleMessage}</p>
                    )}
                    <div className="btn-grid-roles" id="option-buttons">
                        <button onClick={(e) => handleHeroRoleSelection('Tank')} className="btn-role" id="button1"><img className="role-icon" src={athena}></img>Tank</button>
                        <button onClick={(e) => handleHeroRoleSelection('Healer')} className="btn-role" id="button2"><img className="role-icon" src={antibiotics}></img>Healer</button>
                        <button onClick={(e) => handleHeroRoleSelection('DPS')} className="btn-role" id="button3"><img className="role-icon" src={ninja}></img>DPS</button>
                        <button onClick={(e) => handleHeroRoleSelection('Caster')} className="btn-role" id="button4"><img className="role-icon" src={wizard}></img>Caster</button>
                    </div>
                    <button onClick={handleFormSubmit} className="" id="button5">Create Hero</button>
                </div>
            </div>
        </div>
    )
};
  
  export default CreateNewHero;