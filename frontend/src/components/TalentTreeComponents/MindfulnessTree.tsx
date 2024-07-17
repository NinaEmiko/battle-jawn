import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";

const MindfulnessTree = ({props}:{props:any}) => {

    const handleTalentClick = (talent: string, description: string, type: string) => {
        props.setTalentPopUp(talent, description, type);
    }

    return (
        <div className="talent-group-jawn">

        <div className="left-and-right-container-jawn">

            <div className="left-container-jawn">

                {props.resourcefulness1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Resourcefulness 1", talentDescriptions("Resourcefulness 1"), "active")}>Resourcefulness 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Resourcefulness 1", talentDescriptions("Resourcefulness 1"), "available")}>Resourcefulness 1</button>
                }

                {props.resourcefulness2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Resourcefulness 2", "Resourcefulness 2", "active")}>Resourcefulness 2</button>
                }
                {!props.resourcefulness2 && !props.resourcefulness1 &&
                    <button className="talent-jawn-inactive">Resourcefulness 2</button>
                }
                {!props.resourcefulness2 && props.resourcefulness1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Resourcefulness 2", "Resourcefulness 2", "available")}>Resourcefulness 2</button>
                }

                {props.frostBite &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("FrostBite", "FrostBite", "active")}>FrostBite</button>
                }
                {!props.frostBite && !props.resourcefulness2 &&
                    <button className="talent-jawn-inactive">FrostBite</button>
                }
                {!props.frostBite && props.resourcefulness2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("FrostBite", "FrostBite", "available")}>FrostBite</button>
                }


            </div>

            <div className="right-container-jawn">

                {props.botany1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Botany 1", talentDescriptions("Botany 1 - Mindfulness"), "active")}>Botany 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Botany 1", talentDescriptions("Botany 1 - Mindfulness"), "available")}>Botany 1</button>
                }

                {props.botany2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Botany 2", "Botany 2 - Mindfulness", "active")}>Botany 2</button>
                }
                {!props.botany2 && !props.botany1 &&
                    <button className="talent-jawn-inactive">Botany 2</button>
                }
                {!props.botany2 && props.botany1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Botany 2", "Botany 2 - Mindfulness", "available")}>Botany 2</button>
                }

                {props.botany3 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Botany 3", "Botany 2 - Mindfulness", "active")}>Botany 3</button>
                }
                {!props.botany3 && !props.botany2 &&
                    <button className="talent-jawn-inactive">Botany 3</button>
                }
                {!props.botany3 && props.botany2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Botany 3", "Botany 3 - Mindfulness", "available")}>Botany 3</button>
                }

            </div>
            </div>

            <div className="bottom-container-jawn">

            {props.preparation &&
                <button className="talent-jawn-active center-jawn" onClick={() => handleTalentClick("Preparation", "Botany 3 - Mindfulness", "active")}>Preparation</button>
            }
            {!props.preparation && props.improvedWand2 && props.botany3 ||
            !props.preparation && props.improvedWand3 && props.botany2 ?
                <button className="talent-jawn-available center-jawn" onClick={() => handleTalentClick("Preparation", "Botany 3 - Mindfulness", "available")}>Preparation</button>
            :
                <button className="talent-jawn-inactive center-jawn">Preparation</button>
            }

            </div>
        </div>
    )
}
export default MindfulnessTree;