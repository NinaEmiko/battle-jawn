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

                {props.improvedWand1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Wand 1", talentDescriptions("Improved Wand 1"), "active")}>Improved Wand 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Wand 1", talentDescriptions("Improved Wand 1"), "available")}>Improved Wand 1</button>
                }

                {props.improvedWand2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Wand 2", "Description", "active")}>Improved Wand 2</button>
                }
                {!props.improvedWand2 && !props.improvedWand1 &&
                    <button className="talent-jawn-inactive">Improved Wand 2</button>
                }
                {!props.improvedWand2 && props.improvedWand1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Wand 2", "Description", "available")}>Improved Wand 2</button>
                }

                {props.improvedWand3 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Wand 3", "Description", "active")}>Improved Wand 3</button>
                }
                {!props.improvedWand3 && !props.improvedWand2 &&
                    <button className="talent-jawn-inactive">Improved Wand 3</button>
                }
                {!props.improvedWand3 && props.improvedWand2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Wand 3", "Description", "available")}>Improved Wand 3</button>
                }


            </div>

            <div className="right-container-jawn">

                {props.botany1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Botany 1", talentDescriptions("Botany 1"), "active")}>Botany 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Botany 1", talentDescriptions("Botany 1"), "available")}>Botany 1</button>
                }

                {props.botany2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Botany 2", "Description", "active")}>Botany 2</button>
                }
                {!props.botany2 && !props.botany1 &&
                    <button className="talent-jawn-inactive">Botany 2</button>
                }
                {!props.botany2 && props.botany1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Botany 2", "Description", "available")}>Botany 2</button>
                }

                {props.botany3 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Botany 3", "Description", "active")}>Botany 3</button>
                }
                {!props.botany3 && !props.botany2 &&
                    <button className="talent-jawn-inactive">Botany 3</button>
                }
                {!props.botany3 && props.botany2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Botany 3", "Description", "available")}>Botany 3</button>
                }

            </div>
            </div>

            <div className="bottom-container-jawn">

            {props.preparation &&
                <button className="talent-jawn-active center-jawn" onClick={() => handleTalentClick("Preparation", "Description", "active")}>Preparation</button>
            }
            {!props.preparation && props.improvedWand2 && props.botany3 ||
            !props.preparation && props.improvedWand3 && props.botany2 ?
                <button className="talent-jawn-available center-jawn" onClick={() => handleTalentClick("Preparation", "Description", "available")}>Preparation</button>
            :
                <button className="talent-jawn-inactive center-jawn">Preparation</button>
            }

            </div>
        </div>
    )
}
export default MindfulnessTree;