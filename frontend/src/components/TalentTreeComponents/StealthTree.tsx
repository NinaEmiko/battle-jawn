import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";

const StealthTree = ({props}:{props:any}) => {

    const handleTalentClick = (talent: string, description: string, type: string) => {
        props.setTalentPopUp(talent, description, type);
    }

    return (
        <div className="talent-group-jawn">
            
            <div className="left-and-right-container-jawn">

            <div className="left-container-jawn">

                {props.talentTree.improvedSteal1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Steal 1", talentDescriptions("Improved Steal 1"), "active")}>Improved Steal 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Steal 1", talentDescriptions("Improved Steal 1"), "available")}>Improved Steal 1</button>
                }

                {props.talentTree.improvedSteal2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Steal 2", "Improved Steal 2", "active")}>Improved Steal 2</button>
                }
                {!props.talentTree.improvedSteal2 && !props.talentTree.improvedSteal1 &&
                    <button className="talent-jawn-inactive">Improved Steal 2</button>
                }
                {!props.talentTree.improvedSteal2 && props.talentTree.improvedSteal1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Steal 2", "Improved Steal 2", "available")}>Improved Steal 2</button>
                }

                {props.talentTree.honorAmongThieves &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Honor Among Thieves", "Honor Among Thieves", "active")}>Honor Among Thieves</button>
                }
                {!props.talentTree.honorAmongThieves && !props.talentTree.improvedSteal2 &&
                    <button className="talent-jawn-inactive">Honor Among Thieves</button>
                }
                {!props.talentTree.honorAmongThieves && props.talentTree.improvedSteal2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Honor Among Thieves", "Honor Among Thieves", "available")}>Honor Among Thieves</button>
                }


            </div>

            <div className="right-container-jawn">

                {props.talentTree.peekaboo ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Peekaboo", talentDescriptions("Peekaboo"), "active")}>Peekaboo</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Peekaboo", talentDescriptions("Peekaboo"), "available")}>Peekaboo</button>
                }

                {props.talentTree.firstStrike &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("First Strike", "Description", "active")}>First Strike</button>
                }
                {!props.talentTree.firstStrike && !props.talentTree.peekaboo &&
                    <button className="talent-jawn-inactive">First Strike</button>
                }
                {!props.talentTree.firstStrike && props.talentTree.peekaboo &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("First Strike", "First Strike", "available")}>First Strike</button>
                }

                {props.talentTree.elation &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Elation", "Elation", "active")}>Elation</button>
                }
                {!props.talentTree.elation && !props.talentTree.firstStrike &&
                    <button className="talent-jawn-inactive">Elation</button>
                }
                {!props.talentTree.elation && props.talentTree.firstStrike &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Elation", "Elation", "available")}>Elation</button>
                }

            </div>
            </div>

            <div className="bottom-container-jawn">

            {props.stickyFingaz &&
                <button className="talent-jawn-active center-jawn" onClick={() => handleTalentClick("Sticky Fingaz", "Sticky Fingaz", "active")}>Sticky Fingaz</button>
            }
            {!props.stickyFingaz && props.firstStrike && props.honorAmongThieves ||
            !props.stickyFingaz && props.elation && props.improvedSteal2 ?
                <button className="talent-jawn-available center-jawn" onClick={() => handleTalentClick("Sticky Fingaz", "Sticky Fingaz", "available")}>Sticky Fingaz</button>
            :
                <button className="talent-jawn-inactive center-jawn">Sticky Fingaz</button>
            }

            </div>
        </div>
    )
}
export default StealthTree;