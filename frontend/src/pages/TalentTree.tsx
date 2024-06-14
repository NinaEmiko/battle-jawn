import { useNavigate } from "react-router-dom";
import Container from "../components/Container";
import Controls from "../components/Controls";
import Display from "../components/Display";
import PageName from "../components/PageName";
import "../styling/TalentTree.css";
import { useEffect, useState } from "react";

const TalentTree = ({props}:{props:any}) => {
    const [activeButton, setActiveButton] = useState("");
    const [treeOne, setTreeOne] = useState("");
    const [treeTwo, setTreeTwo] = useState("");

    const handleTabClick = (button: string) => {
        setActiveButton(button);
      };

      const navigate = useNavigate();

      const handleNavigation = (path: string) => {
          navigate(path);
      };
    
      useEffect(() => {
        switch (props.heroRole){
            case "Tank":
                setTreeOne("Defense");
                setTreeTwo("Strength");
                break;
            case "Healer":
                setTreeOne("Protection");
                setTreeTwo("Spirituality");
                break;
            case "Caster":
                setTreeOne("Arcane");
                setTreeTwo("Mindfulness");
                break;
            case "DPS":
                setTreeOne("Dexterity");
                setTreeTwo("Stealth");
                break;
        }
            
      })

    return (        
        <Container>
            <PageName>
                <div className="page-name-column-1">
                    {/* <button className="page-name-btn">Sign Out</button> */}
                </div>
                <div className="page-name-column-2">
                    <div className="page-name-txt">Talent Tree</div>
                </div>
                <div className="page-name-column-3">
                    {/* <button className="page-name-btn">New Hero</button> */}
                </div>
            </PageName>
            <Display>
                    <div className="parent-jawn">
                        <div className="child-jawn">
                            <p className="about-us-txt"></p>
                        </div>
                    </div>

                    <div className="display-jawn-tabs">
                        <button className={activeButton === treeOne ? 'active' : ''} onClick={()=> handleTabClick(treeOne)}>{treeOne}</button>
                        <button className={activeButton === treeTwo ? 'active' : ''} onClick={()=> handleTabClick(treeTwo)}>{treeTwo}</button>
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
                        <button className="controls-btn" onClick={()=> handleTabClick(treeOne)}>Left</button>
                        <button className="controls-btn"></button>
                        <button className="controls-btn" onClick={()=> handleTabClick(treeTwo)}>Right</button>
                        <button className="controls-btn"></button>
                    </div>
                </>
            </Controls>
        </Container>
    )
}

export default TalentTree;