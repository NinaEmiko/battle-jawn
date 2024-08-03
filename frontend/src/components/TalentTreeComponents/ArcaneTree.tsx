import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";
import TalentButtonActive from "../TalentComponents/TalentButtonActive";
import TalentButtonAvailable from "../TalentComponents/TalentButtonAvailable";
import TalentButtonInactive from "../TalentComponents/TalentButtonInactive";

const ArcaneTree = ({props}:{props:any}) => {

    const handleTalentClick = (talent: string, description: string, type: string) => {
        props.setTalentPopUp(talent, description, type);
    }

    return (
        <div className="talent-group-jawn">
            <div className="left-and-right-container-jawn">
                <div className="left-container-jawn">
                    {props.talentTree.improvedFireBlast1 ?
                        <TalentButtonActive 
                            props={{
                                text: "Improved FireBlast 1",
                                description: "Improved FireBlast 1",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    :
                        <TalentButtonAvailable 
                            props={{
                                text: "Improved FireBlast 1",
                                description: "Improved FireBlast 1",
                                handleClickTalent: handleTalentClick
                            }}/>                           
                    }
                    {props.talentTree.improvedFireBlast2 &&
                        <TalentButtonActive 
                            props={{
                                text: "Improved FireBlast 2",
                                description: "Improved FireBlast 2",
                                handleClickTalent: handleTalentClick
                            }}/>                      
                    }
                    {!props.talentTree.improvedFireBlast2 && !props.talentTree.improvedFireBlast1 &&
                        <TalentButtonInactive 
                            props={{
                                text: "Improved FireBlast 2"
                            }}/>                      
                    }
                    {!props.talentTree.improvedFireBlast2 && props.talentTree.improvedBlast1 &&
                        <TalentButtonAvailable 
                            props={{
                                text: "Improved FireBlast 2",
                                description: "Improved FireBlast 2",
                                handleClickTalent: handleTalentClick
                            }}/>                     
                    }
                    {props.talentTree.improvedIceBlast &&
                        <TalentButtonActive 
                            props={{
                                text: "Improved IceBlast",
                                description: "Improved IceBlast",
                                handleClickTalent: handleTalentClick
                            }}/>                       
                    }
                    {!props.talentTree.improvedIceBlast && !props.talentTree.improvedFireBlast2 &&
                        <TalentButtonInactive 
                            props={{
                                text: "Improved IceBlast"
                            }}/>                       
                    }
                    {!props.talentTree.improvedIceBlast && props.talentTree.improvedFireBlast2 &&
                        <TalentButtonAvailable 
                            props={{
                                text: "Improved IceBlast",
                                description: "Improved IceBlast",
                                handleClickTalent: handleTalentClick
                            }}/>                       
                    }
                </div>
                <div className="right-container-jawn">
                    {props.talentTree.improvedWand1 ?
                         <TalentButtonActive 
                            props={{
                                text: "Improved Wand 1",
                                description: "Improved Wand 1 - Arcane",
                                handleClickTalent: handleTalentClick
                            }}/>   
                    :
                    <TalentButtonAvailable 
                        props={{
                            text: "Improved Wand 1",
                            description: "Improved Wand 1 - Arcane",
                            handleClickTalent: handleTalentClick
                        }}/>                       
                    }
                    {props.talentTree.improvedWand2 &&
                        <TalentButtonActive 
                            props={{
                                text: "Improved Wand 2",
                                description: "Improved Wand 2 - Arcane",
                                handleClickTalent: handleTalentClick
                            }}/>                       
                    }
                    {!props.talentTree.improvedWand2 && !props.talentTree.improvedWand1 &&
                        <TalentButtonInactive 
                            props={{
                                text: "Improved Wand 2"
                            }}/>                       
                    }
                    {!props.talentTree.improvedWand2 && props.talentTree.improvedWand1 &&
                        <TalentButtonAvailable 
                            props={{
                                text: "Improved Wand 2",
                                description: "Improved Wand 2 - Arcane",
                                handleClickTalent: handleTalentClick
                            }}/>                           }

                    {props.talentTree.improvedWand3 &&
                        <TalentButtonActive 
                            props={{
                                text: "Improved Wand 3",
                                description: "Improved Wand 3 - Arcane",
                                handleClickTalent: handleTalentClick
                            }}/>                     
                    }
                    {!props.talentTree.improvedWand3 && !props.talentTree.improvedWand2 &&
                        <TalentButtonInactive 
                            props={{
                                text: "Improved Wand 3"
                            }}/>                     
                    }
                    {!props.talentTree.improvedWand3 && props.talentTree.improvedWand2 &&
                        <TalentButtonAvailable 
                            props={{
                                text: "Improved Wand 3",
                                description: "Improved Wand 3 - Arcane",
                                handleClickTalent: handleTalentClick
                            }}/>                        
                    }
                </div>
            </div>

            <div className="bottom-container-jawn">

                {props.talentTree.secondNature &&
                    <TalentButtonActive 
                        props={{
                            text: "Second Nature",
                            description: "Second Nature",
                            handleClickTalent: handleTalentClick
                        }}/>                     
                }
                {!props.talentTree.secondNature && 
                    <>
                        {props.talentTree.improvedIceBlast && props.talentTree.resourcefulness2 ||
                        props.talentTree.improvedFireBlast2 && props.talentTree.frostBite ?
                            <TalentButtonAvailable 
                                props={{
                                    text: "Second Nature",
                                    description: "Second Nature",
                                    handleClickTalent: handleTalentClick
                                }}/>                        
                            :
                            <TalentButtonInactive 
                                props={{
                                    text: "Second Nature"
                                }}/>   
                        }            
                    </>  
                }
            </div>
        </div>
    )
}
export default ArcaneTree;