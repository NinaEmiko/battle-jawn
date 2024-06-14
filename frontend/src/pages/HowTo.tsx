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