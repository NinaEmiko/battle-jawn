import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";
import TalentButtonActive from "../TalentComponents/TalentButtonActive";
import TalentButtonAvailable from "../TalentComponents/TalentButtonAvailable";
import TalentButtonInactive from "../TalentComponents/TalentButtonInactive";

const DefenseTree = ({props}:{props:any}) => {

    const handleTalentClick = (talent: string, description: string, type: string) => {
        props.setTalentPopUp(talent, description, type);
    }

    return (
        <div className="talent-group-jawn">
            <div className="left-and-right-container-jawn">
                <div className="left-container-jawn">
                    {props.talentTree.improvedHealth1 ?
                        <TalentButtonActive 
                            props={{
                                text: "Improved Health 1",
                                description: "Improved Health 1",
                                handleClickTalent: handleTalentClick
                            }}/>                   
                    :
                        <TalentButtonAvailable 
                            props={{
                                text: "Improved Health 1",
                                description: "Improved Health 1",
                                handleClickTalent: handleTalentClick
                            }}/>                  
                    }
                    {props.talentTree.improvedHealth2 &&
                        <TalentButtonActive 
                            props={{
                                text: "Improved Health 2",
                                description: "Improved Health 2",
                                handleClickTalent: handleTalentClick
                            }}/>                     
                    }
                    {!props.talentTree.improvedHealth2 && !props.talentTree.improvedHealth1 &&
                        <TalentButtonInactive 
                            props={{
                                text: "Improved Health 2"
                            }}/>                     
                    }
                    {!props.talentTree.improvedHealth2 && props.talentTree.improvedHealth1 &&
                        <TalentButtonAvailable 
                            props={{
                                text: "Improved Health 2",
                                description: "Improved Health 2",
                                handleClickTalent: handleTalentClick
                            }}/>                   
                    }
                    {props.talentTree.hydration &&
                        <TalentButtonActive 
                            props={{
                                text: "Hydration",
                                description: "Hydration",
                                handleClickTalent: handleTalentClick
                            }}/>                  
                    }
                    {!props.talentTree.hydration && !props.talentTree.improvedHealth2 &&
                        <TalentButtonInactive 
                            props={{
                                text: "Hydration"
                            }}/>                
                    }
                    {!props.talentTree.hydration && props.talentTree.improvedHealth2 &&
                        <TalentButtonAvailable 
                            props={{
                                text: "Hydration",
                                description: "Hydration",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    }
                </div>
                <div className="right-container-jawn">
                    {props.talentTree.improvedBlock1 ?
                        <TalentButtonActive 
                            props={{
                                text: "Improved Block 1",
                                description: "Improved Block 1",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    :
                        <TalentButtonAvailable 
                            props={{
                                text: "Improved Block 1",
                                description: "Improved Block 1",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    }

                    {props.talentTree.improvedBlock2 &&
                        <TalentButtonActive 
                            props={{
                                text: "Improved Block 2",
                                description: "Improved Block 2",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    }
                    {!props.talentTree.improvedBlock2 && !props.talentTree.improvedBlock1 &&
                        <TalentButtonInactive 
                            props={{
                                text: "Improved Block 2"
                            }}/>                
                    }
                    {!props.talentTree.improvedBlock2 && props.talentTree.improvedBlock1 &&
                        <TalentButtonAvailable 
                            props={{
                                text: "Improved Block 2",
                                description: "Improved Block 2",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    }
                    {props.talentTree.finalStand &&
                        <TalentButtonActive 
                            props={{
                                text: "Final Stand",
                                description: "Final Stand",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    }
                    {!props.talentTree.finalStand && !props.talentTree.improvedBlock2 &&
                        <TalentButtonInactive 
                            props={{
                                text: "Final Stand"
                            }}/>                
                    }
                    {!props.talentTree.finalStand && props.talentTree.improvedBlock2 &&
                        <TalentButtonAvailable 
                            props={{
                                text: "Final Stand",
                                description: "Final Stand",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    }
                </div>
            </div>
            <div className="bottom-container-jawn">
            {props.talentTree.desperation &&
                <TalentButtonActive 
                    props={{
                        text: "Desperation",
                        description: "Desperation",
                        handleClickTalent: handleTalentClick
                    }}/>            
            }
            {!props.talentTree.desperation &&
                <>
                    {props.talentTree.finalStand && props.talentTree.improvedHealth2 ||
                    props.talentTree.hydration && props.talentTree.improvedBlock2 ?
                        <TalentButtonAvailable 
                            props={{
                                text: "Desperation",
                                description: "Desperation",
                                handleClickTalent: handleTalentClick
                            }}/>              
                    :
                        <TalentButtonInactive 
                            props={{
                                text: "Desperation"
                            }}/>            
                    }
                </>
            }
            </div>
        </div>
    )
}
export default DefenseTree;