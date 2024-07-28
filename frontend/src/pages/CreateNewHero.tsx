import { useEffect, useState } from "react";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";
import Tank from "../assets/roles/Tank.png"
import Healer from "../assets/roles/Healer.png"
import DPS from "../assets/roles/DPS.png"
import Caster from "../assets/roles/Caster.png"
import { CASTER_DESCRIPTION, DPS_DESCRIPTION, HEALER_DESCRIPTION, TANK_DESCRIPTION } from "../helpers/content_helper";
import "../styling/CreateNewHero.css";
import "../styling/Tabs.css";
import { newHero } from "../api/api";
  
function CreateNewHero({props}:{props:any}): React.ReactNode {
    const [heroName, setHeroName] = useState('');
    const [activeButton, setActiveButton] = useState("Tank");
    const [heroDescription, setHeroDescription] = useState(TANK_DESCRIPTION);
    const [message, setMessage] = useState("");
    const roles = ["Tank", "Healer", "DPS", "Caster"]

    const handleTabClick = (button: string) => {
        setActiveButton(button);
      };

    const handleReturnToHeroes = (id: number) => {
        props.setIsVisible("Heroes", id);
    }

    const newHeroCall = async () => {
        const data = await newHero(props.accountId, heroName, activeButton);
        handleReturnToHeroes(data.id);
    }

    const handleFormSubmit = async () => {
        if (!heroName) {
            setMessage('WARNING: Must enter a name to continue.');
        } else {
            newHeroCall()
        }
    }

    const handleLeftButtonClick = () => {
        const currentIndex = roles.indexOf(activeButton);
        const newIndex = (currentIndex - 1 + roles.length) % roles.length;
        setActiveButton(roles[newIndex]);
    };

    const handleRightButtonClick = () => {
        const currentIndex = roles.indexOf(activeButton);
        const newIndex = (currentIndex + 1 + roles.length) % roles.length;
        setActiveButton(roles[newIndex]);
    };
    
    useEffect(() => {
        switch (activeButton){
            case "Tank":
                setHeroDescription(TANK_DESCRIPTION);
                break;
            case "Healer":
                setHeroDescription(HEALER_DESCRIPTION);
                break;
            case "DPS":
                setHeroDescription(DPS_DESCRIPTION);
                break;
            case "Caster":
                setHeroDescription(CASTER_DESCRIPTION);
                break;
        }
      }, [activeButton])

    return (
        <Container>
            <PageName props={"New Hero"} />
            <Display>
                <div className="parent-jawn">
                    <div className="display-tabs-top-4">
                        <button className={activeButton === 'Tank' ? 'active' : ''} onClick={()=> handleTabClick("Tank")}>Tank</button>
                        <button className={activeButton === 'Healer' ? 'active' : ''} onClick={()=> handleTabClick("Healer")}>Healer</button>
                        <button className={activeButton === 'DPS' ? 'active' : ''} onClick={()=> handleTabClick("DPS")}>DPS</button>
                        <button className={activeButton === 'Caster' ? 'active' : ''} onClick={()=> handleTabClick("Caster")}>Caster</button>
                    </div>

                    {activeButton != "" &&
                            <input type="text"
                            className="new-hero-name-input"
                            placeholder="Hero Name"
                            value={heroName} onChange={(e) => setHeroName(e.target.value)} />
                    }

                    {message !== "" &&
                        <div className="warning-txt">
                            {message}
                        </div>
                    }

                    <div className="image-description-jawn">
                        <div className="image-jawn">
                            {activeButton === "Tank" &&
                                <img className="hero-img" src={Tank} />
                            }

                            {activeButton === "Healer" &&
                                <img className="hero-img" src={Healer} />
                            }

                            {activeButton === "DPS" &&
                                <img className="hero-img" src={DPS} />
                            }

                            {activeButton === "Caster" &&
                                <img className="hero-img" src={Caster} />
                            }
                        </div>
                        <div className="description-jawn">
                            <p className="text-jawn">{heroDescription}</p>
                        </div>
                    </div>
                </div>
            </Display>
            <Controls
                handleClickLeftBtnBottom={() => handleReturnToHeroes(0)}
                leftBtnBottomText="Back"
                handleClickRightBtnLeft={() => handleLeftButtonClick()}
                rightBtnLeftText="ᐊ"
                handleClickLeftBtnTop={handleFormSubmit}
                leftBtnTopText="OK"
                handleClickRightBtnRight={() => handleRightButtonClick()}
                rightBtnRightText="ᐅ"
                rightBtnTopText="ᐃ"
                rightBtnBottomText="ᐁ"
            />
        </Container>

        
    )
};
  
  export default CreateNewHero;