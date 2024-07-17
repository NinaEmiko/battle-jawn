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
import TalentPopUp from "./TalentPopUp";

const TalentTree = ({props}:{props:any}) => {
    const [treeOne, setTreeOne] = useState("");
    const [treeTwo, setTreeTwo] = useState("");
    const [activeTree, setActiveTree] = useState("");
    const [popUpType, setPopUpType] = useState("");
    const [popUpTalent, setPopUpTalent] = useState("");
    const [popUpDescription, setPopUpDescription] = useState("");
    const [showPopUp, setShowPopUp] = useState(false);

    const handleTabClick = (button: string) => {
        setActiveTree(button);
    };

    const handleOkButtonClick = () => {
        setShowPopUp(false);
    }

    const handleConfirmButtonClick = () => {
        setShowPopUp(false);
    }

    const handleSubComponentButtonClick = (talent: string, description: string, type: string) => {
        setPopUpType(type);
        setPopUpTalent(talent);
        setPopUpDescription(description);
        setShowPopUp(true);
      }
    
    useEffect(() => {
        if (activeTree === "" && treeOne === "" && treeTwo === ""){
            let returnTreeOne = treeOneSetter(props.role);
            let returnTreeTwo = treeTwoSetter(props.role);

            setTreeOne(returnTreeOne);
            setActiveTree(returnTreeOne);
            setTreeTwo(returnTreeTwo);
        }
    })
    useEffect(() => {
        if (activeTree === "" && treeOne === "" && treeTwo === ""){
            let returnTreeOne = treeOneSetter(props.role);
            let returnTreeTwo = treeTwoSetter(props.role);

            setTreeOne(returnTreeOne);
            setActiveTree(returnTreeOne);
            setTreeTwo(returnTreeTwo);
        }
    }, [])

    return (        
        <>
            {showPopUp ?
                <TalentPopUp 
                    props={{
                        type: popUpType,
                        talent: popUpTalent,
                        description: popUpDescription,
                        onClickOk: handleOkButtonClick,
                        onClickConfirm: handleConfirmButtonClick
                    }} 
                />   
            :
            <div>
                <div className="hero-name-level">
                    <div className="hero-name">
                        {props.name}
                    </div>
                    <div className="hero-level">
                    {props.talentPoints} talent points
                    </div>
                </div>
                <div>
                    {props.role === "Tank" &&
                        <div>
                            {activeTree === treeOne &&
                                <DefenseTree props={{talentTree: props.talentTree, setTalentPopUp: handleSubComponentButtonClick}} />
                            }
                            {activeTree === treeTwo &&
                                <StrengthTree props={{talentTree: props.talentTree, setTalentPopUp: handleSubComponentButtonClick}} />
                            }
                        </div>
                    }
                    {props.role === "Healer" &&
                        <div>
                            {activeTree === treeOne &&
                                <ProtectionTree props={{talentTree: props.talentTree, setTalentPopUp: handleSubComponentButtonClick}} />
                            }
                            {activeTree === treeTwo &&
                                <SpiritualityTree props={{talentTree: props.talentTree, setTalentPopUp: handleSubComponentButtonClick}} />
                            }
                        </div>
                    }
                    {props.role === "DPS" &&
                        <div>
                            {activeTree === treeTwo &&
                                <StealthTree props={{talentTree: props.talentTree, setTalentPopUp: handleSubComponentButtonClick}} />
                            }
                            {activeTree === treeOne &&
                                <DexterityTree props={{talentTree: props.talentTree, setTalentPopUp: handleSubComponentButtonClick}} />
                            }
                        </div>
                    }
                    {props.role === "Caster" &&
                        <div>
                            {activeTree === treeOne &&
                                <ArcaneTree props={{talentTree: props.talentTree, setTalentPopUp: handleSubComponentButtonClick}} />
                            }
                            {activeTree === treeTwo &&
                                <MindfulnessTree props={{talentTree: props.talentTree, setTalentPopUp: handleSubComponentButtonClick}} />
                            }
                        </div>
                    }
                </div>
                <div className="talent-trees-jawn">
                    <button className={activeTree === treeOne ? 'talent-tree-jawn-active' : 'talent-tree-jawn'} onClick={()=> handleTabClick(treeOne)}>{treeOne}</button>
                    <button className={activeTree === treeTwo ? 'talent-tree-jawn-active' : 'talent-tree-jawn'} onClick={()=> handleTabClick(treeTwo)}>{treeTwo}</button>
                </div>
            </div>
            }
        </>
    )

}

export default TalentTree;