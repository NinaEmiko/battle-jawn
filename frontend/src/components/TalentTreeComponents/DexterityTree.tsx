import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";
import TalentButtonActive from "../TalentComponents/TalentButtonActive";
import TalentButtonAvailable from "../TalentComponents/TalentButtonAvailable";
import TalentButtonInactive from "../TalentComponents/TalentButtonInactive";

const DexterityTree = ({props}:{props:any}) => {

    const handleTalentClick = (talent: string, description: string, type: string) => {
        props.setTalentPopUp(talent, description, type);
    }

    return (
        <div className="talent-group-jawn">
            <div className="left-and-right-container-jawn">
                <div className="left-container-jawn">
                    {props.talentTree.improvedStab1 ?
                        <TalentButtonActive
                            props={{
                                text: "Improved Stab 1",
                                description: "Improved Stab 1",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    :
                        <TalentButtonAvailable
                            props={{
                                text: "Improved Stab 1",
                                description: "Improved Stab 1",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    }
                    {props.talentTree.improvedStab2 &&
                        <TalentButtonActive
                            props={{
                                text: "Improved Stab 2",
                                description: "Improved Stab 2",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    }
                    {!props.talentTree.improvedStab2 && !props.talentTree.improvedStab1 &&
                        <TalentButtonInactive
                            props={{
                                text: "Improved Stab 2"
                            }}/>                
                    }
                    {!props.talentTree.improvedStab2 && props.talentTree.improvedStab1 &&
                        <TalentButtonAvailable
                            props={{
                                text: "Improved Stab 2",
                                description: "Improved Stab 2",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    }
                    {props.talentTree.improvedStab3 &&
                        <TalentButtonActive
                            props={{
                                text: "Improved Stab 3",
                                description: "Improved Stab 3",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    }
                    {!props.talentTree.improvedStab3 && !props.talentTree.improvedStab2 &&
                        <TalentButtonInactive
                            props={{
                                text: "Improved Stab 3"
                            }}/>                    
                    }
                    {!props.talentTree.improvedStab3 && props.talentTree.improvedStab2 &&
                        <TalentButtonAvailable
                            props={{
                                text: "Improved Stab 3",
                                description: "Improved Stab 3",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    }
                </div>
                <div className="right-container-jawn">
                    {props.talentTree.improvedBackStab1 ?
                        <TalentButtonActive
                            props={{
                                text: "Improved BackStab 1",
                                description: "Improved BackStab 1",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    :
                        <TalentButtonAvailable
                            props={{
                                text: "Improved BackStab 1",
                                description: "Improved BackStab 1",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    }
                    {props.talentTree.improvedBackStab2 &&
                        <TalentButtonActive
                            props={{
                                text: "Improved BackStab 2",
                                description: "Improved BackStab 2",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    }
                    {!props.talentTree.improvedBackStab2 && !props.talentTree.improvedBackStab1 &&
                        <TalentButtonInactive
                            props={{
                                text: "Improved BackStab 2"
                            }}/>                    
                    }
                    {!props.talentTree.improvedBackStab2 && props.talentTree.improvedBackStab1 &&
                        <TalentButtonAvailable
                            props={{
                                text: "Improved BackStab 2",
                                description: "Improved BackStab 2",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    }
                    {props.talentTree.energized &&
                        <TalentButtonActive
                            props={{
                                text: "Energized",
                                description: "Energized",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    }
                    {!props.talentTree.energized && !props.talentTree.improvedBackStab2 &&
                        <TalentButtonInactive
                            props={{
                                text: "Energized"
                            }}/>                    
                    }
                    {!props.talentTree.energized && props.talentTree.improvedBackStab2 &&
                        <TalentButtonAvailable
                            props={{
                                text: "Energized",
                                description: "Energized",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    }
                </div>
            </div>
            <div className="bottom-container-jawn">
                {props.talentTree.organizedMess &&
                    <TalentButtonActive
                        props={{
                            text: "Organized Mess",
                            description: "Organized Mess",
                            handleClickTalent: handleTalentClick,
                            bottom: true
                        }}/>                
                }
                {!props.talentTree.organizedMess && 
                    <>
                        {props.talentTree.improvedStab2 && props.talentTree.energized ||
                        props.talentTree.improvedBackStab2 && props.talentTree.improvedStab3 ?
                            <TalentButtonAvailable
                                props={{
                                    text: "Organized Mess",
                                    description: "Organized Mess",
                                    handleClickTalent: handleTalentClick,
                                    bottom: true
                                }}/>                
                        :
                            <TalentButtonActive
                                props={{
                                    text: "Organized Mess",
                                    bottom: true
                                }}/>                
                        }
                    </>
                }
            </div>
        </div>
    )
}
export default DexterityTree;