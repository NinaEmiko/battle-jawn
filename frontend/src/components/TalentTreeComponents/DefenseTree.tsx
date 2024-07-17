import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";

const DefenseTree = ({props}:{props:any}) => {

    const handleTalentClick = (talent: string, description: string, type: string) => {
        props.setTalentPopUp(talent, description, type);
    }

    return (
        <div className="talent-group-jawn">

            <div className="left-and-right-container-jawn">

            <div className="left-container-jawn">

                {props.improvedHealth1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Health 1", talentDescriptions("Improved Health 1"), "active")}>Improved Health 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Health 1", talentDescriptions("Improved Health 1"), "available")}>Improved Health 1</button>
                }

                {props.improvedHealth2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Health 2", "Improved Health 2", "active")}>Improved Health 2</button>
                }
                {!props.improvedHealth2 && !props.improvedHealth1 &&
                    <button className="talent-jawn-inactive">Improved Health 2</button>
                }
                {!props.improvedHealth2 && props.improvedHealth1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Health 2", "Improved Health 2", "available")}>Improved Health 2</button>
                }

                {props.hydration &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Hydration", "Hydration", "active")}>Hydration</button>
                }
                {!props.hydration && !props.improvedHealth2 &&
                    <button className="talent-jawn-inactive">Hydration</button>
                }
                {!props.hydration && props.improvedHealth2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Hydration", "Hydration", "available")}>Hydration</button>
                }


            </div>

            <div className="right-container-jawn">

                {props.improvedBlock1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Block 1", talentDescriptions("Improved Block 1"), "active")}>Improved Block 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Block 1", talentDescriptions("Improved Block 1"), "available")}>Improved Block 1</button>
                }

                {props.improvedBlock2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Block 2", "Improved Block 2", "active")}>Improved Block 2</button>
                }
                {!props.improvedBlock2 && !props.improvedBlock1 &&
                    <button className="talent-jawn-inactive">Improved Block 2</button>
                }
                {!props.improvedBlock2 && props.improvedBlock1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Block 2", "Improved Block 2", "available")}>Improved Block 2</button>
                }

                {props.finalStand &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Final Stand", "Final Stand", "active")}>Final Stand</button>
                }
                {!props.finalStand && !props.improvedBlock2 &&
                    <button className="talent-jawn-inactive">Final Stand</button>
                }
                {!props.finalStand && props.improvedBlock2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Final Stand", "Final Stand", "available")}>Final Stand</button>
                }

            </div>
            </div>

            <div className="bottom-container-jawn">

            {props.desperation &&
                <button className="talent-jawn-active center-jawn" onClick={() => handleTalentClick("Desperation", "Desperation", "active")}>Desperation</button>
            }
            {!props.desperation && props.finalStand && props.improvedHealth2 ||
            !props.desperation && props.hydration && props.improvedBlock2 ?
                <button className="talent-jawn-available center-jawn" onClick={() => handleTalentClick("Desperation", "Desperation", "available")}>Desperation</button>
            :
                <button className="talent-jawn-inactive center-jawn">Desperation</button>
            }

            </div>

        </div>
    )
}
export default DefenseTree;