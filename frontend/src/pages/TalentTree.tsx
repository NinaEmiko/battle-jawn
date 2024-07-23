import { useEffect, useState } from "react";
import "../styling/TalentTree.css";
import "../styling/MyHeroes.css";
import { treeOneSetter, treeTwoSetter } from "../helpers/talent_tree_helper";
import StealthTree from "../components/TalentTreeComponents/StealthTree";
import DexterityTree from "../components/TalentTreeComponents/DexterityTree";
import SpiritualityTree from "../components/TalentTreeComponents/SpiritualityTree";
import ProtectionTree from "../components/TalentTreeComponents/ProtectionTree";
import StrengthTree from "../components/TalentTreeComponents/StrengthTree";
import DefenseTree from "../components/TalentTreeComponents/DefenseTree";
import ArcaneTree from "../components/TalentTreeComponents/ArcaneTree";
import MindfulnessTree from "../components/TalentTreeComponents/MindfulnessTree";
import TalentPopUp from "../components/TalentTreeComponents/TalentPopUp";
import { fetchHero } from "../api/api";
import Container from "../components/Container";
import PageName from "../components/PageName";
import Display from "../components/Display";
import Controls from "../components/Controls";

const TalentTree = ({props}:{props:any}) => {
    const [treeOne, setTreeOne] = useState("");
    const [heroName, setHeroName] = useState("");
    const [heroRole, setHeroRole] = useState("");
    const [heroTalentPoints, setHeroTalentPoints] = useState("");
    const [heroTalentTree, setHeroTalentTree] = useState("");
    const [treeTwo, setTreeTwo] = useState("");
    const [activeTree, setActiveTree] = useState("");
    const [popUpType, setPopUpType] = useState("");
    const [popUpTalent, setPopUpTalent] = useState("");
    const [popUpDescription, setPopUpDescription] = useState("");
    const [showPopUp, setShowPopUp] = useState(false);

    const handleFetchHero = async () => {
        const data = await fetchHero(props.id)
        setHeroName(data.name);
        setHeroRole(data.role);
        handleSetTrees(data.role);
        setHeroTalentPoints(data.talentPoints);
        setHeroTalentTree(data.talentTree);
    }

    const handleTabClick = (button: string) => {
        setActiveTree(button);
    };

    const handleOkButtonClick = () => {
        handleFetchHero();
        setShowPopUp(false);
    }
    const handleBackButtonClick = () => {
        props.setIsVisible("Heroes", props.id);
    }

    const handleConfirmButtonClick = async (talent: string) => {
        handleFetchHero();
        setShowPopUp(false);
    }

    const handleSubComponentButtonClick = (talent: string, description: string, type: string) => {
        setPopUpType(type);
        setPopUpTalent(talent);
        setPopUpDescription(description);
        setShowPopUp(true);
      }

    const handleSetTrees = (role: string) => {
        setTreeOne(treeOneSetter(role));
        setActiveTree(treeOneSetter(role));
        setTreeTwo(treeTwoSetter(role));
    }

    useEffect(() => {
        if (heroName == ""){
            handleFetchHero();
        }
    },[])

    return (  
        <>
            {!showPopUp ?      
                <Container>
                    <PageName props={"Talents"} />
                    <Display>
                        <>
                            <div>
                                <div className="hero-name-level">
                                    <div className="hero-name">
                                        {heroName}
                                    </div>
                                    <div className="hero-level">
                                    {heroTalentPoints} talent points
                                    </div>
                                </div>
                                <div>
                                    {heroRole === "Tank" &&
                                        <div>
                                            {activeTree === treeOne &&
                                                <DefenseTree props={{talentTree: heroTalentTree, setTalentPopUp: handleSubComponentButtonClick}} />
                                            }
                                            {activeTree === treeTwo &&
                                                <StrengthTree props={{talentTree: heroTalentTree, setTalentPopUp: handleSubComponentButtonClick}} />
                                            }
                                        </div>
                                    }
                                    {heroRole === "Healer" &&
                                        <div>
                                            {activeTree === treeOne &&
                                                <ProtectionTree props={{talentTree: heroTalentTree, setTalentPopUp: handleSubComponentButtonClick}} />
                                            }
                                            {activeTree === treeTwo &&
                                                <SpiritualityTree props={{talentTree: heroTalentTree, setTalentPopUp: handleSubComponentButtonClick}} />
                                            }
                                        </div>
                                    }
                                    {heroRole === "DPS" &&
                                        <div>
                                            {activeTree === treeTwo &&
                                                <StealthTree props={{talentTree: heroTalentTree, setTalentPopUp: handleSubComponentButtonClick}} />
                                            }
                                            {activeTree === treeOne &&
                                                <DexterityTree props={{talentTree: heroTalentTree, setTalentPopUp: handleSubComponentButtonClick}} />
                                            }
                                        </div>
                                    }
                                    {heroRole === "Caster" &&
                                        <div>
                                            {activeTree === treeOne &&
                                                <ArcaneTree props={{talentTree: heroTalentTree, setTalentPopUp: handleSubComponentButtonClick}} />
                                            }
                                            {activeTree === treeTwo &&
                                                <MindfulnessTree props={{talentTree: heroTalentTree, setTalentPopUp: handleSubComponentButtonClick}} />
                                            }
                                        </div>
                                    }
                                </div>
                                    <div className="talent-trees-jawn">
                                        <button className={activeTree === treeOne ? 'talent-tree-jawn-active' : 'talent-tree-jawn'} onClick={()=> handleTabClick(treeOne)}>{treeOne}</button>
                                        <button className={activeTree === treeTwo ? 'talent-tree-jawn-active' : 'talent-tree-jawn'} onClick={()=> handleTabClick(treeTwo)}>{treeTwo}</button>
                                    </div>
                            </div>
                        </>
                    </Display>
                    <Controls 
                        handleClickLeftBtnBottom={() => handleBackButtonClick()}
                        leftBtnBottomText="Back"
                        handleClickRightBtnLeft={() => handleTabClick(treeOne)}
                        rightBtnLeftText="Left"
                        handleClickRightBtnRight={() => handleTabClick(treeTwo)}
                        rightBtnRightText="Right"
                    />
                </Container>
            :
                <TalentPopUp 
                    props={{
                        type: popUpType,
                        talent: popUpTalent,
                        description: popUpDescription,
                        heroId: props.id,
                        onClickOk: handleOkButtonClick,
                        onClickConfirm: handleConfirmButtonClick
                    }} 
                />   
            }
        </>
    )

}

export default TalentTree;