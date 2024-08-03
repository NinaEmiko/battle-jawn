import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";
import TalentButton from "../TalentComponents/TalentButtonActive";
import TalentButtonActive from "../TalentComponents/TalentButtonActive";
import TalentButtonAvailable from "../TalentComponents/TalentButtonAvailable";
import TalentButtonInactive from "../TalentComponents/TalentButtonInactive";

const SpiritualityTree = ({props}:{props:any}) => {

    const handleTalentClick = (talent: string, description: string, type: string) => {
        props.setTalentPopUp(talent, description, type);
    }

    return (
        <div className="talent-group-jawn">
            <div className="left-and-right-container-jawn">
                <div className="left-container-jawn">
                    {props.talentTree.improvedWand1 ?
                        <TalentButtonActive 
                            props={{
                                text: "Improved Wand 1",
                                description: "Improved Wand 1 - Spirituality",
                                handleClickTalent: handleTalentClick
                            }}/>
                    :
                        <TalentButtonAvailable 
                            props={{
                                text: "Improved Wand 1",
                                description: "Improved Wand 1 - Spirituality",
                                handleClickTalent: handleTalentClick
                            }}/>
                    }
                    {props.talentTree.improvedWand2 &&
                        <TalentButtonAvailable 
                            props={{
                                text: "Improved Wand 2",
                                description: "Improved Wand 2 - Spirituality",
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
                                description: "Improved Wand 2 - Spirituality",
                                handleClickTalent: handleTalentClick
                            }}/>
                    }
                    {props.talentTree.improvedWand3 &&
                        <TalentButtonActive 
                            props={{
                                text: "Improved Wand 3",
                                description: "Improved Wand 3 - Spirituality",
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
                                description: "Improved Wand 3 - Spirituality",
                                handleClickTalent: handleTalentClick
                            }}/>
                    }
                </div>

                <div className="right-container-jawn">
                    {props.talentTree.improvedHoly1 ?
                        <TalentButtonActive
                            props={{
                                text: "Improved Holy 1",
                                description: "Improved Holy 1",
                                handleClickTalent: handleTalentClick
                            }}/>
                    :
                        <TalentButtonAvailable
                            props={{
                                text: "Improved Holy 1",
                                description: "Improved Holy 1",
                                handleClickTalent: handleTalentClick
                            }}/>
                    }
                    {props.talentTree.improvedHoly2 &&
                        <TalentButtonActive
                            props={{
                                text: "Improved Holy 2",
                                description: "Improved Holy 2",
                                handleClickTalent: handleTalentClick
                            }}/>
                    }
                    {!props.talentTree.improvedHoly2 && !props.talentTree.improvedHoly1 &&
                        <TalentButtonInactive
                            props={{
                                text: "Improved Holy 2"
                            }}/>
                    }
                    {!props.talentTree.improvedHoly2 && props.talentTree.improvedHoly1 &&
                        <TalentButtonAvailable
                            props={{
                                text: "Improved Holy 2",
                                description: "Improved Holy 2",
                                handleClickTalent: handleTalentClick
                            }}/>                
                    }
                    {props.talentTree.improvedHoly3 &&
                        <TalentButtonActive
                            props={{
                                text: "Improved Holy 3",
                                description: "Improved Holy 3",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    }
                    {!props.talentTree.improvedHoly3 && !props.talentTree.improvedHoly2 &&
                        <TalentButtonInactive
                            props={{
                                text: "Improved Holy 3"
                            }}/>                    
                    }
                    {!props.talentTree.improvedHoly3 && props.talentTree.improvedHoly2 &&
                        <TalentButtonAvailable
                            props={{
                                text: "Improved Holy 3",
                                description: "Improved Holy 3",
                                handleClickTalent: handleTalentClick
                            }}/>                    
                    }
                </div>
            </div>
            <div className="bottom-container-jawn">
                {props.talentTree.spirituallyAttuned &&
                    <TalentButtonActive
                        props={{
                            text: "Spiritually Attuned",
                            description: "Spiritually Attuned",
                            handleClickTalent: handleTalentClick
                        }}/>               
                }
                {!props.talentTree.spirituallyAttuned &&
                    <>
                        {props.talentTree.improvedWand3 && props.talentTree.improvedHoly2 ||
                        props.talentTree.improvedWand2 && props.talentTree.improvedHoly3 ?
                            <TalentButtonAvailable
                                props={{
                                    text: "Spiritually Attuned",
                                    description: "Spiritually Attuned",
                                    handleClickTalent: handleTalentClick
                                }}/>              
                            :
                            <TalentButtonInactive
                                props={{
                                    text: "Spiritually Attuned"
                                }}/>              
                        }
                    </>
                }
            </div>
        </div>
    )
}
export default SpiritualityTree;