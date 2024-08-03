import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";
import TalentButtonActive from "../TalentComponents/TalentButtonActive";
import TalentButtonAvailable from "../TalentComponents/TalentButtonAvailable";
import TalentButtonInactive from "../TalentComponents/TalentButtonInactive";

const StealthTree = ({props}:{props:any}) => {

    const handleTalentClick = (talent: string, description: string, type: string) => {
        props.setTalentPopUp(talent, description, type);
    }

    return (
        <div className="talent-group-jawn">
            
            <div className="left-and-right-container-jawn">

            <div className="left-container-jawn">

                {props.talentTree.improvedSteal1 ?
                    <TalentButtonActive 
                        props={{
                            text: "Improved Steal 1",
                            description: "Improved Steal 1",
                            handleClickTalent: handleTalentClick
                        }}/>
                :
                    <TalentButtonAvailable 
                        props={{
                            text: "Improved Steal 1",
                            description: "Improved Steal 1",
                            handleClickTalent: handleTalentClick
                        }}/>
                }

                {props.talentTree.improvedSteal2 &&
                    <TalentButtonActive 
                        props={{
                            text: "Improved Steal 2",
                            description: "Improved Steal 2",
                            handleClickTalent: handleTalentClick
                        }}/>
                }
                {!props.talentTree.improvedSteal2 && !props.talentTree.improvedSteal1 &&
                    <TalentButtonInactive 
                        props={{
                            text: "Improved Steal 2"
                        }}/>
                }
                {!props.talentTree.improvedSteal2 && props.talentTree.improvedSteal1 &&
                    <TalentButtonAvailable 
                        props={{
                            text: "Improved Steal 2",
                            description: "Improved Steal 2",
                            handleClickTalent: handleTalentClick
                        }}/>
                }

                {props.talentTree.honorAmongThieves &&
                    <TalentButtonActive 
                        props={{
                            text: "Honor Among Thieves",
                            description: "Honor Among Thieves",
                            handleClickTalent: handleTalentClick
                        }}/>
                }
                {!props.talentTree.honorAmongThieves && !props.talentTree.improvedSteal2 &&
                    <TalentButtonInactive 
                        props={{
                            text: "Honor Among Thieves"
                        }}/>
                }
                {!props.talentTree.honorAmongThieves && props.talentTree.improvedSteal2 &&
                    <TalentButtonAvailable 
                        props={{
                            text: "Honor Among Thieves",
                            description: "Honor Among Thieves",
                            handleClickTalent: handleTalentClick
                        }}/>
                }
            </div>
            <div className="right-container-jawn">
                {props.talentTree.peekaboo ?
                    <TalentButtonActive 
                        props={{
                            text: "Peekaboo",
                            description: "Peekaboo",
                            handleClickTalent: handleTalentClick
                        }}/>
                :
                    <TalentButtonAvailable 
                        props={{
                            text: "Peekaboo",
                            description: "Peekaboo",
                            handleClickTalent: handleTalentClick
                        }}/>
                }

                {props.talentTree.firstStrike &&
                    <TalentButtonActive 
                        props={{
                            text: "First Strike",
                            description: "First Strike",
                            handleClickTalent: handleTalentClick
                        }}/>
                }
                {!props.talentTree.firstStrike && !props.talentTree.peekaboo &&
                    <TalentButtonInactive 
                        props={{
                            text: "First Strike"
                        }}/>
                }
                {!props.talentTree.firstStrike && props.talentTree.peekaboo &&
                    <TalentButtonAvailable
                        props={{
                            text: "First Strike",
                            description: "First Strike",
                            handleClickTalent: handleTalentClick
                        }}/>
                }
                {props.talentTree.elation &&
                    <TalentButtonActive 
                        props={{
                            text: "Elation",
                            description: "Elation",
                            handleClickTalent: handleTalentClick
                        }}/>
                }
                {!props.talentTree.elation && !props.talentTree.firstStrike &&
                    <TalentButtonInactive 
                        props={{
                            text: "Elation"
                        }}/>
                }
                {!props.talentTree.elation && props.talentTree.firstStrike &&
                    <TalentButtonActive 
                        props={{
                            text: "Elation",
                            description: "Elation",
                            handleClickTalent: handleTalentClick
                        }}/>
                }
            </div>
            </div>
            <div className="bottom-container-jawn">
                {props.stickyFingaz &&
                    <TalentButtonActive 
                        props={{
                            text: "Sticky Fingaz",
                            description: "Sticky Fingaz",
                            handleClickTalent: handleTalentClick,
                            bottom: true
                        }}/>
                }
                {!props.stickyFingaz &&
                    <>
                        {props.firstStrike && props.honorAmongThieves ||
                        props.elation && props.improvedSteal2 ?
                            <TalentButtonAvailable 
                                props={{
                                    text: "Sticky Fingaz",
                                    description: "Sticky Fingaz",
                                    handleClickTalent: handleTalentClick,
                                    bottom: true
                                }}/>
                        :
                            <TalentButtonInactive 
                                props={{
                                    text: "Sticky Fingaz",
                                    bottom: true
                                }}/>
                        }
                    </>
                }
            </div>
        </div>
    )
}
export default StealthTree;