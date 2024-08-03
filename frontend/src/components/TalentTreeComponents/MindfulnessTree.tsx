import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";
import TalentButtonActive from "../TalentComponents/TalentButtonActive";
import TalentButtonAvailable from "../TalentComponents/TalentButtonAvailable";
import TalentButtonInactive from "../TalentComponents/TalentButtonInactive";

const MindfulnessTree = ({props}:{props:any}) => {

    const handleTalentClick = (talent: string, description: string, type: string) => {
        props.setTalentPopUp(talent, description, type);
    }

    return (
        <div className="talent-group-jawn">
            <div className="left-and-right-container-jawn">
                <div className="left-container-jawn">
                    {props.talentTree.resourcefulness1 ?
                        <TalentButtonActive 
                            props={{
                                text: "Resourcefulness 1",
                                description: "Resourcefulness 1",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    :
                        <TalentButtonAvailable 
                            props={{
                                text: "Resourcefulness 1",
                                description: "Resourcefulness 1",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    }
                    {props.talentTree.resourcefulness2 &&
                        <TalentButtonActive 
                            props={{
                                text: "Resourcefulness 2",
                                description: "Resourcefulness 2",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    }
                    {!props.talentTree.resourcefulness2 && !props.talentTree.resourcefulness1 &&
                        <TalentButtonInactive 
                            props={{
                                text: "Resourcefulness 2"
                            }}/>                
                    }
                    {!props.talentTree.resourcefulness2 && props.talentTree.resourcefulness1 &&
                        <TalentButtonAvailable 
                            props={{
                                text: "Resourcefulness 2",
                                description: "Resourcefulness 2",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    }
                    {props.talentTree.frostBite &&
                        <TalentButtonActive 
                            props={{
                                text: "FrostBite",
                                description: "FrostBite",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    }
                    {!props.talentTree.frostBite && !props.talentTree.resourcefulness2 &&
                        <TalentButtonInactive 
                            props={{
                                text: "FrostBite"
                            }}/>                
                    }
                    {!props.talentTree.frostBite && props.talentTree.resourcefulness2 &&
                        <TalentButtonAvailable
                            props={{
                                text: "FrostBite",
                                description: "FrostBite",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    }
                </div>
                <div className="right-container-jawn">
                    {props.talentTree.botany1 ?
                        <TalentButtonActive 
                            props={{
                                text: "Botany 1",
                                description: "Botany 1 - Mindfulness",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    :
                        <TalentButtonAvailable 
                            props={{
                                text: "Botany 1",
                                description: "Botany 1 - Mindfulness",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    }
                    {props.talentTree.botany2 &&
                        <TalentButtonActive 
                            props={{
                                text: "Botany 2",
                                description: "Botany 2 - Mindfulness",
                                handleClickTalent: handleTalentClick
                            }}/>                 
                    }
                    {!props.talentTree.botany2 && !props.talentTree.botany1 &&
                        <TalentButtonInactive 
                            props={{
                                text: "Botany 2"
                            }}/>                 
                    }
                    {!props.talentTree.botany2 && props.talentTree.botany1 &&
                        <TalentButtonActive 
                            props={{
                                text: "Botany 2",
                                description: "Botany 2 - Mindfulness",
                                handleClickTalent: handleTalentClick
                            }}/>                 
                    }
                    {props.talentTree.botany3 &&
                        <TalentButtonActive 
                            props={{
                                text: "Botany 3",
                                description: "Botany 3 - Mindfulness",
                                handleClickTalent: handleTalentClick
                            }}/>                 
                    }
                    {!props.talentTree.botany3 && !props.talentTree.botany2 &&
                        <TalentButtonInactive 
                            props={{
                                text: "Botany 3"
                            }}/>                 
                    }
                    {!props.talentTree.botany3 && props.talentTree.botany2 &&
                        <TalentButtonAvailable 
                            props={{
                                text: "Botany 3",
                                description: "Botany 3 - Mindfulness",
                                handleClickTalent: handleTalentClick
                            }}/>                 
                    }
                </div>
                </div>
                <div className="bottom-container-jawn">
                {props.talentTree.preparation &&
                    <TalentButtonActive 
                        props={{
                            text: "Preparation",
                            description: "Preparation",
                            handleClickTalent: handleTalentClick,
                            bottom: true
                        }}/>                 
                }
                {!props.talentTree.preparation && 
                    <>
                        {props.talentTree.improvedWand2 && props.talentTree.botany3 ||
                        props.talentTree.improvedWand3 && props.talentTree.botany2 ?
                            <TalentButtonAvailable
                                props={{
                                    text: "Preparation",
                                    description: "Preparation",
                                    handleClickTalent: handleTalentClick,
                                    bottom: true
                                }}/>                 
                        :
                            <TalentButtonInactive
                                props={{
                                    text: "Preparation",
                                    bottom: true
                                }}/>                
                        }
                    </>
                }
            </div>
        </div>
    )
}
export default MindfulnessTree;