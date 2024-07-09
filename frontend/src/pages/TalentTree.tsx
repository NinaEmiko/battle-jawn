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

const TalentTree = ({props}:{props:any}) => {
    const [treeOne, setTreeOne] = useState("");
    const [treeTwo, setTreeTwo] = useState("");
    const [activeTree, setActiveTree] = useState("");

    const handleTabClick = (button: string) => {
        setActiveTree(button);
      };
    
    useEffect(() => {
        if (activeTree === "" && treeOne === "" && treeTwo === ""){
            let returnTreeOne = treeOneSetter(props.role);
            let returnTreeTwo = treeTwoSetter(props.role);

            setTreeOne(returnTreeOne);
            setActiveTree(returnTreeOne);
            setTreeTwo(returnTreeTwo);
        }
    })

    return (        

        <>
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
                            <DefenseTree />
                        }
                        {activeTree === treeTwo &&
                            <StrengthTree />
                        }
                    </div>
                }
                {props.role === "Healer" &&
                    <div>
                        {activeTree === treeOne &&
                            <ProtectionTree />
                        }
                        {activeTree === treeTwo &&
                            <SpiritualityTree />
                        }
                    </div>
                }
                {props.role === "DPS" &&
                    <div>
                        {activeTree === treeTwo &&
                            <StealthTree />
                        }
                        {activeTree === treeOne &&
                            <DexterityTree />
                        }
                    </div>
                }
                {props.role === "Caster" &&
                    <div>
                        {activeTree === treeOne &&
                            <ArcaneTree />
                        }
                        {activeTree === treeTwo &&
                            <MindfulnessTree />
                        }
                    </div>
                }
            </div>
            <div className="talent-trees-jawn">
                <button className={activeTree === treeOne ? 'talent-tree-jawn-active' : 'talent-tree-jawn'} onClick={()=> handleTabClick(treeOne)}>{treeOne}</button>
                <button className={activeTree === treeTwo ? 'talent-tree-jawn-active' : 'talent-tree-jawn'} onClick={()=> handleTabClick(treeTwo)}>{treeTwo}</button>
            </div>
        </>
    )

}

export default TalentTree;