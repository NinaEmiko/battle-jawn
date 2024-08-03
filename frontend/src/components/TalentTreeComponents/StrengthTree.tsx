import "../../styling/TalentTree.css";
import TalentButtonActive from "../TalentComponents/TalentButtonActive";
import TalentButtonAvailable from "../TalentComponents/TalentButtonAvailable";
import TalentButtonInactive from "../TalentComponents/TalentButtonInactive";

const StrengthTree = ({props}:{props:any}) => {

    const handleTalentClick = (talent: string, description: string, type: string) => {
        props.setTalentPopUp(talent, description, type);
    }

    return (
        <div className="talent-group-jawn">
            <div className="left-and-right-container-jawn">
                <div className="left-container-jawn">
                    {props.talentTree.improvedStrike1 ?
                        <TalentButtonActive 
                            props={{
                                text: "Improved Strike 1",
                                description: "Improved Strike 1",
                                handleClickTalent: handleTalentClick
                            }}/>
                    :
                        <TalentButtonAvailable 
                            props={{
                                text: "Improved Strike 1",
                                description: "Improved Strike 1",
                                handleClickTalent: handleTalentClick
                            }}/>
                    }

                    {props.talentTree.improvedStrike2 &&
                        <TalentButtonActive 
                            props={{
                                text: "Improved Strike 2",
                                description: "Improved Strike 2",
                                handleClickTalent: handleTalentClick
                            }}/>
                    }
                    {!props.talentTree.improvedStrike2 && !props.talentTree.improvedStrike1 &&
                        <TalentButtonInactive 
                            props={{
                                text: "Improved Strike 2"
                            }}/>
                    }
                    {!props.talentTree.improvedStrike2 && props.talentTree.improvedStrike1 &&
                        <TalentButtonAvailable 
                            props={{
                                text: "Improved Strike 2",
                                description: "Improved Strike 2",
                                handleClickTalent: handleTalentClick
                            }}/>
                    }
                    {props.talentTree.improvedStrike3 &&
                        <TalentButtonActive 
                            props={{
                                text: "Improved Strike 3",
                                description: "Improved Strike 3",
                                handleClickTalent: handleTalentClick
                            }}/>
                    }
                    {!props.talentTree.improvedStrike3 && !props.talentTree.improvedStrike2 &&
                        <TalentButtonInactive 
                            props={{
                                text: "Improved Strike 3"
                            }}/>
                    }
                    {!props.talentTree.improvedStrike3 && props.talentTree.improvedStrike2 &&
                        <TalentButtonAvailable 
                            props={{
                                text: "Improved Strike 3",
                                description: "Improved Strike 3",
                                handleClickTalent: handleTalentClick
                            }}/>
                    }
                </div>
                <div className="right-container-jawn">
                    {props.talentTree.improvedImpale1 ?
                    <TalentButtonActive 
                        props={{
                            text: "Improved Impale 1",
                            description: "Improved Impale 1",
                            handleClickTalent: handleTalentClick
                        }}/>
                    :
                        <TalentButtonAvailable 
                            props={{
                                text: "Improved Impale 1",
                                description: "Improved Impale 1",
                                handleClickTalent: handleTalentClick
                            }}/>
                    }
                    {props.talentTree.improvedImpale2 &&
                        <TalentButtonActive 
                            props={{
                                text: "Improved Impale 2",
                                description: "Improved Impale 2",
                                handleClickTalent: handleTalentClick
                            }}/>
                    }
                    {!props.talentTree.improvedImpale2 && !props.talentTree.improvedImpale1 &&
                        <TalentButtonInactive 
                            props={{
                                text: "Improved Impale 2"
                            }}/>
                    }
                    {!props.talentTree.improvedImpale2 && props.talentTree.improvedImpale1 &&
                        <TalentButtonAvailable 
                            props={{
                                text: "Improved Impale 2",
                                description: "Improved Impale 2",
                                handleClickTalent: handleTalentClick
                            }}/>
                    }
                    {props.talentTree.improvedImpale3 &&
                        <TalentButtonActive 
                            props={{
                                text: "Improved Impale 3",
                                description: "Improved Impale 3",
                                handleClickTalent: handleTalentClick
                            }}/>
                    }
                    {!props.talentTree.improvedImpale3 && !props.talentTree.improvedImpale2 &&
                        <TalentButtonInactive 
                            props={{
                                text: "Improved Impale 3"
                            }}/>
                    }
                    {!props.talentTree.improvedImpale3 && props.talentTree.improvedImpale2 &&
                        <TalentButtonAvailable 
                            props={{
                                text: "Improved Impale 3",
                                description: "Improved Impale 3",
                                handleClickTalent: handleTalentClick
                            }}/>
                    }
                </div>
            </div>
            <div className="bottom-container-jawn">

            {props.talentTree.titan &&
                <TalentButtonActive 
                    props={{
                        text: "Titan",
                        description: "Titan",
                        handleClickTalent: handleTalentClick,
                        bottom: true
                    }}/>
            }
            {!props.talentTree.titan &&
                <>
                    {props.talentTree.improvedStrike3 && props.talentTree.improvedImpale2 ||
                    props.talentTree.improvedStrike2 && props.talentTree.improvedImpale3 ?
                        <TalentButtonAvailable 
                            props={{
                                text: "Titan",
                                description: "Titan",
                                handleClickTalent: handleTalentClick,
                                bottom: true
                            }}/>
                    :
                        <TalentButtonInactive 
                            props={{
                                text: "Titan",
                                bottom: true
                            }}/>
                    }
                </>
            }
            </div>
        </div>
    )
}
export default StrengthTree;