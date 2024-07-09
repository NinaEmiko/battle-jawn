import { useNavigate } from "react-router-dom";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";
import "../styling/HowTo.css";
import { useState } from "react";

const HowTo = () => {
    const [activeButton, setActiveButton] = useState("Heroes");
    const [activeRole, setActiveRole] = useState("Tank");
    const [playPage, setPlayPage] = useState(1);

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

    const handleUpClick = () => {
        if (activeButton === "Battle"){
            switch (activeRole){
                case "Tank":
                    setActiveRole("Caster");
                    break;
                case "Healer":
                    setActiveRole("Tank");
                    break;
                case "DPS":
                    setActiveRole("Healer");
                    break;
                case "Caster":
                    setActiveRole("DPS");
                    break;
            }
        }
        if (activeButton === "Play"){
            switch (playPage){
                case 1:
                    setPlayPage(2);
                    break;
                case 2:
                    setPlayPage(1);
                    break;
            }
        }
    };

    const handleDownClick = () => {
        if (activeButton === "Battle"){
            switch (activeRole){
                case "Tank":
                    setActiveRole("Healer");
                    break;
                case "Healer":
                    setActiveRole("DPS");
                    break;
                case "DPS":
                    setActiveRole("Caster");
                    break;
                case "Caster":
                    setActiveRole("Tank");
                    break;
            }
        }
        if (activeButton === "Play"){
            switch (playPage){
                case 1:
                    setPlayPage(2);
                    break;
                case 2:
                    setPlayPage(1);
                    break;
            }
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

                                    <p className="battle-role">{activeRole}</p>

                                    {activeRole === "Tank" &&
                                        <div>
                                            <p className="battle-move">Strike: Generates 1 Power.</p>
                                            <p className="battle-move">Impale: Consumes 2 Power.</p>
                                            <p className="battle-move">Block: Successful blocks generate Max Power.</p>
                                        </div>
                                    }

                                    {activeRole === "Healer" &&
                                        <div>
                                            <p className="battle-move">Wand: Generates 1 Spirit.</p>
                                            <p className="battle-move">Holy: Consumes 2 Spirit.</p>
                                            <p className="battle-move">Heal: Consumes 1 Spirit.</p>
                                        </div>
                                    }

                                    {activeRole === "DPS" &&
                                        <div>
                                            <p className="battle-move">Stab: Generates 1 Energy.</p>
                                            <p className="battle-move">FireBlast: Consumes 3 Magic.</p>
                                            <p className="battle-move">IceBlast: Consumes 1 Magic.</p>
                                        </div>
                                    }   

                                    {activeRole === "Caster" &&
                                        <div>
                                            <p className="battle-move">Wand: Generates 1 Magic.</p>
                                            <p className="battle-move">FireBlast: Consumes 3 Magic.</p>
                                            <p className="battle-move">IceBlast: Consumes 1 Magic.</p>
                                        </div>
                                    }   
                                    <p className="battle-howto">Press up or down to scroll through classes.</p>
                                </div>
                            }

                            {activeButton === "Play" &&
                                <div className="battle">

                                    {playPage === 1 &&
                                        <div>
                                            <p style={{color: "white"}}>Arena: Enter to fight enemies.</p>
                                            <p style={{color: "white"}}>Shop: Enter to purchase and sell items.</p>
                                        </div>
                                    }

                                    {playPage === 2 &&
                                    <div>
                                        <p>Currently Unavailable:</p>
                                        <p style={{color: "white"}}>Leaving Town</p>
                                        <p style={{color: "white"}}>Entering Town Homes</p>
                                        <p style={{color: "white"}}>Hospital</p>
                                        <p style={{color: "white"}}>Hostel</p>
                                        <p style={{color: "white"}}>NPCs</p>
                                        <p style={{color: "white"}}>Quests</p>
                                    </div>
                                    }
                                </div>
                            }

                            {activeButton === "Leader Board" &&
                                <div className="battle">
                                    <p style={{color: "white"}}>Press up or down to scroll through the current Top 5 heroes</p>
                                </div>
                            }

                            {activeButton === "Heroes" &&
                                <div className="battle">
                                    <p>Currently Unavailable:</p>
                                    <p style={{color: "white"}}>Talent Tree</p>                                
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
                        <button className="controls-btn" onClick={()=> handleUpClick()}>Up</button>
                        <button className="controls-btn" onClick={()=> handleTabLeftClick()}>Left</button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn" onClick={()=> handleTabRightClick()}>Right</button>
                        <button className="controls-btn" onClick={()=> handleDownClick()}>Down</button>
                    </div>
                </>
            </Controls>
        </Container>
    )
}

export default HowTo;