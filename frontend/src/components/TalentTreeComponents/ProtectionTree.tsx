import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";
import TalentButtonActive from "../TalentComponents/TalentButtonActive";
import TalentButtonAvailable from "../TalentComponents/TalentButtonAvailable";
import TalentButtonInactive from "../TalentComponents/TalentButtonInactive";

const ProtectionTree = ({props}:{props:any}) => {

    const handleTalentClick = (talent: string, description: string, type: string) => {
        props.setTalentPopUp(talent, description, type);
    }

    return (
        <div className="talent-group-jawn">
            <div className="left-and-right-container-jawn">
                <div className="left-container-jawn">
                    {props.talentTree.improvedHeal1 ?
                        <TalentButtonActive
                            props={{
                                text: "Improved Heal 1",
                                description: "Improved Heal 1",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    :
                        <TalentButtonAvailable
                            props={{
                                text: "Improved Heal 1",
                                description: "Improved Heal 1",
                                handleClickTalent: handleTalentClick
                            }}/>                        
                    }
                    {props.talentTree.improvedHeal2 &&
                        <TalentButtonActive
                            props={{
                                text: "Improved Heal 2",
                                description: "Improved Heal 2",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    }
                    {!props.talentTree.improvedHeal2 && !props.talentTree.improvedHeal1 &&
                        <TalentButtonInactive
                            props={{
                                text: "Improved Heal 2"
                            }}/>                    
                    }
                    {!props.talentTree.improvedHeal2 && props.talentTree.improvedHeal1 &&
                        <TalentButtonAvailable
                            props={{
                                text: "Improved Heal 2",
                                description: "Improved Heal 2",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    }
                    {props.talentTree.improvedHeal3 &&
                        <TalentButtonActive
                            props={{
                                text: "Improved Heal 3",
                                description: "Improved Heal 3",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    }
                    {!props.talentTree.improvedHeal3 && !props.talentTree.improvedHeal2 &&
                        <TalentButtonInactive
                            props={{
                                text: "Improved Heal 3"
                            }}/>                    
                    }
                    {!props.talentTree.improvedHeal3 && props.talentTree.improvedHeal2 &&
                        <TalentButtonAvailable
                            props={{
                                text: "Improved Heal 3",
                                description: "Improved Heal 3",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    }
                </div>
                <div className="right-container-jawn">
                    {props.talentTree.botany1 ?
                        <TalentButtonActive
                            props={{
                                text: "Botany 1",
                                description: "Botany 1 - Protection",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    :
                        <TalentButtonAvailable
                            props={{
                                text: "Botany 1",
                                description: "Botany 1 - Protection",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    }
                    {props.talentTree.botany2 &&
                        <TalentButtonActive
                            props={{
                                text: "Botany 2",
                                description: "Botany 2 - Protection",
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
                        <TalentButtonAvailable
                            props={{
                                text: "Botany 2",
                                description: "Botany 2 - Protection",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    }
                    {props.talentTree.bubble &&
                        <TalentButtonActive
                            props={{
                                text: "Bubble",
                                description: "Bubble",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    }
                    {!props.talentTree.bubble && !props.talentTree.botany2 &&
                        <TalentButtonInactive
                            props={{
                                text: "Bubble"
                            }}/>                     
                    }
                    {!props.talentTree.bubble && props.talentTree.botany2 &&
                        <TalentButtonAvailable
                            props={{
                                text: "Bubble",
                                description: "Bubble",
                                handleClickTalent: handleTalentClick
                            }}/>                     
                    }
                </div>
            </div>
            <div className="bottom-container-jawn">
                {props.talentTree.survivalInstincts &&
                    <TalentButtonActive
                        props={{
                            text: "Survival Instincts",
                            description: "Survival Instincts",
                            handleClickTalent: handleTalentClick
                        }}/>                
                }
                {!props.talentTree.survivalInstincts &&
                    <>
                        {props.talentTree.improvedHeal3 && props.talentTree.botany2 ||
                        props.talentTree.improvedHeal2 && props.talentTree.botany3 ?
                            <TalentButtonAvailable
                                props={{
                                    text: "Survival Instincts",
                                    description: "Survival Instincts",
                                    handleClickTalent: handleTalentClick
                                }}/>                        
                        :
                            <TalentButtonInactive
                                props={{
                                    text: "Survival Instincts"
                                }}/>                        
                        }
                    </>
                }
            </div>
        </div>
    )
}
export default ProtectionTree;