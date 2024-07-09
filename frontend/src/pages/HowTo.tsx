import { useNavigate } from "react-router-dom";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";
import "../styling/HowTo.css";
import { useState } from "react";
import HowToArena from "../components/HowToComponents/HowToArena";
import HowToPlay from "../components/HowToComponents/HowToPlay";
import HowToBag from "../components/HowToComponents/HowToBag";
import HowToComingSoon from "../components/HowToComponents/HowToComingSoon";
import HowToHeroes from "../components/HowToComponents/HowToHeroes";
import HowToBattleTank from "../components/HowToComponents/HowToBattleTank";
import HowToBattleHealer from "../components/HowToComponents/HowToBattleHealer";
import HowToBattleDPS from "../components/HowToComponents/HowToBattleDPS";
import HowToBattleCaster from "../components/HowToComponents/HowToBattleCaster";

const HowTo = () => {
    const [activeButton, setActiveButton] = useState("Heroes");
    const [activeRole, setActiveRole] = useState("Tank");
    const [playPage, setPlayPage] = useState(1);

    const handleTabLeftClick = () => {
        switch (activeButton){
            case "Heroes":
                setActiveButton("Coming Soon");
                break;
            case "Battle":
                setActiveButton("Heroes");
                break;
            case "Play":
                setActiveButton("Battle");
                break;
            case "Coming Soon":
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
                setActiveButton("Coming Soon");
                break;
            case "Coming Soon":
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
                    setPlayPage(3);
                    break;
                case 2:
                    setPlayPage(1);
                    break;
                case 3:
                    setPlayPage(2);
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
                    setPlayPage(3);
                    break;
                case 3:
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
                            <button className={activeButton === 'Coming Soon' ? 'active' : ''} onClick={()=> handleClickSpecificTab("Coming Soon")}>Coming Soon</button>
                        </div>
                            {activeButton === 'Battle' &&
                                <div className="battle">

                                    <p className="battle-role">{activeRole}</p>

                                    {activeRole === "Tank" &&
                                        <HowToBattleTank />
                                    }

                                    {activeRole === "Healer" &&
                                        <HowToBattleHealer />
                                    }

                                    {activeRole === "DPS" &&
                                        <HowToBattleDPS />
                                    }   

                                    {activeRole === "Caster" &&
                                        <HowToBattleCaster />
                                    }   

                                    <p className="battle-howto">Press up or down to scroll through classes.</p>
                                </div>
                            }

                            {activeButton === "Play" &&
                                <div className="battle">

                                    {playPage === 1 &&
                                        <HowToArena />
                                    }

                                    {playPage === 2 &&
                                        <HowToPlay />
                                    }
                                    {playPage === 3 &&
                                        <HowToBag />
                                    }
                                    
                                    <p className="battle-howto">Press up or down to scroll through available content.</p>

                                </div>
                            }

                            {activeButton === "Coming Soon" &&
                                 <HowToComingSoon />
                            }

                            {activeButton === "Heroes" &&
                                <HowToHeroes />
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