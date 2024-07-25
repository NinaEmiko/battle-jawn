import { useState } from "react";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";
import Tank from "../assets/Tank.png"
import Healer from "../assets/Healer.png"
import DPS from "../assets/DPS.png"
import Caster from "../assets/Caster.png"
import { CASTER_DESCRIPTION, DPS_DESCRIPTION, HEALER_DESCRIPTION, TANK_DESCRIPTION } from "../helpers/content_helper";
import "../styling/CreateNewHero.css";
import { newHero } from "../api/api";
  
function CreateNewHero({props}:{props:any}): React.ReactNode {
    const [heroName, setHeroName] = useState('');
    const [activeButton, setActiveButton] = useState("Tank");
    const [heroDescription, setHeroDescription] = useState(TANK_DESCRIPTION);
    const [message, setMessage] = useState("");
    const roles = ["Tank", "Healer", "DPS", "Caster"]

    const handleTabClick = (button: string) => {
        setActiveButton(button);
        switch (button){
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

    return (
        <Container>
            <PageName props={"New Hero"} />
            <Display>
                <div className="parent-jawn">
                    <div className="display-jawn-tabs-4">
                        <button className={activeButton === 'Tank' ? 'active' : ''} onClick={()=> handleTabClick("Tank")}>Tank</button>
                        <button className={activeButton === 'Healer' ? 'active' : ''} onClick={()=> handleTabClick("Healer")}>Healer</button>
                        <button className={activeButton === 'DPS' ? 'active' : ''} onClick={()=> handleTabClick("DPS")}>DPS</button>
                        <button className={activeButton === 'Caster' ? 'active' : ''} onClick={()=> handleTabClick("Caster")}>Caster</button>
                    </div>

                    {activeButton != "" &&
                        <div className="new-hero-name">
                            <input type="text"
                            className="new-hero-name-input"
                            placeholder="Hero Name"
                            value={heroName} onChange={(e) => setHeroName(e.target.value)} />
                        </div>
                    }
                    <div className="message-container">
                        {message !== "" &&
                            <div className="message-jawn">
                                {message}
                            </div>
                        }
                    </div>


                    <div className="image-description-jawn">

                        {activeButton === "Tank" &&
                            <div className="hero-img-jawn">
                                <img className="hero-img" src={Tank} />
                            </div>
                        }

                        {activeButton === "Healer" &&
                            <div className="hero-img-jawn">
                                <img className="hero-img" src={Healer} />
                            </div>
                        }

                        {activeButton === "DPS" &&
                            <div className="hero-img-jawn">
                                <img className="hero-img" src={DPS} />
                            </div>
                        }

                        {activeButton === "Caster" &&
                            <div className="hero-img-jawn">
                                <img className="hero-img" src={Caster} />
                            </div>
                        }
                    </div>

                    <div className="hero-description-txt-container">
                        <p className="hero-description-txt">{heroDescription}</p>
                    </div>

                </div>
            </Display>
            <Controls
                handleClickLeftBtnBottom={() => handleReturnToHeroes(0)}
                leftBtnBottomText="Back"
                handleClickRightBtnLeft={() => handleLeftButtonClick()}
                rightBtnLeftText="ᐊ"
                handleClickRightBtnCenter={handleFormSubmit}
                rightBtnCenterText="OK"
                handleClickRightBtnRight={() => handleRightButtonClick()}
                rightBtnRightText="ᐅ"
            />
        </Container>

        
    )
};
  
  export default CreateNewHero;