import { useNavigate } from "react-router-dom";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";
import "../styling/HowTo.css";
import { useState } from "react";

const HowTo = () => {
    const [activeButton, setActiveButton] = useState("Heroes");

    const handleTabLeftClick = () => {
        switch (activeButton){
            case "Heroes":
                setActiveButton("Leader Board");
                break;
            case "Battle":
                setActiveButton("Heroes");
                break;
            case "Play":
                setActiveButton("Battle");
                break;
            case "Leader Board":
                setActiveButton("Play");
                break;
        }

      };

    const handleTabRightClick = () => {
        switch (activeButton){
            case "Heroes":
                setActiveButton("Battle");
                break;
            case "Battle":
                setActiveButton("Play");
                break;
            case "Play":
                setActiveButton("Leader Board");
                break;
            case "Leader Board":
                setActiveButton("Heroes");
                break;
        }

    };

    const handleClickSpecificTab = (tab: string) => {
        setActiveButton(tab);
    }

      const navigate = useNavigate();

      const handleNavigation = (path: string) => {
          navigate(path);
      };

    return (        
        <Container>
            <PageName>
                <div className="page-name-column-1">
                    {/* <button className="page-name-btn">Sign Out</button> */}
                </div>
                <div className="page-name-column-2">
                    <div className="page-name-txt">How To</div>
                </div>
                <div className="page-name-column-3">
                    {/* <button className="page-name-btn">New Hero</button> */}
                </div>
            </PageName>
            <Display>
                    <div className="parent-jawn">
                        <div className="display-jawn-tabs-4">
                            <button className={activeButton === 'Heroes' ? 'active' : ''} onClick={()=> handleClickSpecificTab("Heroes")}>Heroes</button>
                            <button className={activeButton === 'Battle' ? 'active' : ''} onClick={()=> handleClickSpecificTab("Battle")}>Battle</button>
                            <button className={activeButton === 'Play' ? 'active' : ''} onClick={()=> handleClickSpecificTab("Play")}>Play</button>
                            <button className={activeButton === 'Leader Board' ? 'active' : ''} onClick={()=> handleClickSpecificTab("Leader Board")}>Leader Board</button>
                        </div>
                            {activeButton === 'Battle' &&
                                <div className="battle">
                                    <p style={{color: "white"}}>Strike: Generates 1 Power.</p>
                                    <p style={{color: "white"}}>Impale: Consumes 2 Power.</p>
                                    <p style={{color: "white"}}>Block: Successful blocks generate Max Power.</p>
                                    <p style={{color: "white"}}>Wand: Generates 1 Spirit/Magic.</p>
                                    <p style={{color: "white"}}>Holy: Consumes 2 Spirit.</p>
                                    <p style={{color: "white"}}>Heal: Consumes 1 Spirit.</p>
                                    <p style={{color: "white"}}>Stab: Generates 1 Energy.</p>
                                    <p style={{color: "white"}}>BackStab: Consumes 3 Energy.</p>
                                    <p style={{color: "white"}}>Steal: Consumes 1 Energy.</p>
                                    <p style={{color: "white"}}>FireBlast: Consumes 3 Magic.</p>
                                    <p style={{color: "white"}}>IceBlast: Consumes 1 Magic.</p>
                                </div>
                            }

                            {activeButton === "Play" &&
                                <div className="battle">
                                    <p style={{color: "white"}}>Arena: Enter to fight enemies.</p>
                                    <p style={{color: "white"}}>Shop: Enter to purchase and sell items.</p>
                                    <p style={{color: "white"}}>Leaving Town: Currently Unavailable.</p>
                                    <p style={{color: "white"}}>Entering Town Homes: Currently Unavailable.</p>
                                    <p style={{color: "white"}}>Hospital: Currently Unavailable.</p>
                                    <p style={{color: "white"}}>Hostel: Currently Unavailable.</p>
                                    <p style={{color: "white"}}>NPCs: Currently Unavailable.</p>
                                    <p style={{color: "white"}}>Quests: Currently Unavailable.</p>
                                </div>
                            }

                            {activeButton === "Leader Board" &&
                                <div className="battle">
                                    <p style={{color: "white"}}>Press up or down to scroll through the current Top 5 heroes</p>
                                </div>
                            }

                            {activeButton === "Heroes" &&
                                <div className="battle">
                                    <p style={{color: "white"}}>Talent Tree: Currently Unavailable.</p>
                                </div>
                            }
                    </div>
            </Display>
            <Controls>
                <>
                    <div className="controls-left">
                        <button className="controls-btn"></button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn" onClick={() => handleNavigation("/")}>Exit</button>                    
                    </div>
                    <div className="controls-right">
                        <button className="controls-btn"></button>
                        <button className="controls-btn" onClick={()=> handleTabLeftClick()}>Left</button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn" onClick={()=> handleTabRightClick()}>Right</button>
                        <button className="controls-btn"></button>
                    </div>
                </>
            </Controls>
        </Container>
    )
}

export default HowTo;