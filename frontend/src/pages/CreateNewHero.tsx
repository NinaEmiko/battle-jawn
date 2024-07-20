import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { request } from "../helpers/axios_helper";
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
  
function CreateNewHero({props}:{props:any}): React.ReactNode {
    const apiUrl = import.meta.env.VITE_REACT_APP_URL;
    const [heroName, setHeroName] = useState('');
    const [activeButton, setActiveButton] = useState("Tank");
    const [heroDescription, setHeroDescription] = useState("");
    const [message, setMessage] = useState("");

    const handleTabClick = (button: string) => {
        setActiveButton(button);
      };
    const navigate = useNavigate();

    const handleNavigation = (path: string) => {
        navigate(path);
      };

    const handleFormSubmit = async () => {

        if (!heroName) {
            setMessage('WARNING: Must enter a name to continue.');
        } else {
            try {
                const response = await request('POST', apiUrl + '/api/hero', {
                    userAccountId: props.id,
                    heroName: heroName,
                    role: activeButton
                });
          
                console.log('Hero created successfully:', response.data);
                handleNavigation("/");
            } catch (error) {
                console.error('Error creating hero:', error);
            }
        }
    }
    const handleRightButtonClick = () => {
        switch (activeButton){
            case "Tank":
                setActiveButton("Healer");
                break;
            case "Healer":
                setActiveButton("DPS");
                break;
            case "DPS":
                setActiveButton("Caster");
                break;
            case "Caster":
                setActiveButton("Tank");
                break;
        }
    }

    const handleLeftButtonClick = () => {
        switch (activeButton){
            case "Tank":
                setActiveButton("Caster");
                break;
            case "Healer":
                setActiveButton("Tank");
                break;
            case "DPS":
                setActiveButton("Healer");
                break;
            case "Caster":
                setActiveButton("DPS");
                break;
        }
    }

    useEffect(()=>{
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
                handleClickLeftBtnBottom={() => handleNavigation("/")}
                leftBtnBottomText="Back"
                handleClickRightBtnLeft={() => handleLeftButtonClick()}
                rightBtnLeftText="Left"
                handleClickRightBtnCenter={handleFormSubmit}
                rightBtnCenterText="OK"
                handleClickRightBtnRight={() => handleRightButtonClick()}
                rightBtnRightText="Right"
            />
        </Container>

        
    )
};
  
  export default CreateNewHero;