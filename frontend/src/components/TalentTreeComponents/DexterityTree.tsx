import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";

const DexterityTree = ({props}:{props:any}) => {

    const handleTalentClick = (talent: string, description: string, type: string) => {
        props.setTalentPopUp(talent, description, type);
    }

    return (
        <div className="talent-group-jawn">

            <div className="left-and-right-container-jawn">

            <div className="left-container-jawn">

                {props.improvedStab1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Stab 1", talentDescriptions("Improved Stab 1"), "active")}>Improved Stab 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Stab 1", talentDescriptions("Improved Stab 1"), "available")}>Improved Stab 1</button>
                }

                {props.improvedStab2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Stab 2", "Description", "active")}>Improved Stab 2</button>
                }
                {!props.improvedStab2 && !props.improvedStab1 &&
                    <button className="talent-jawn-inactive">Improved Stab 2</button>
                }
                {!props.improvedStab2 && props.improvedStab1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Stab 2", "Description", "available")}>Improved Stab 2</button>
                }

                {props.improvedStab3 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Stab 3", "Description", "active")}>Improved Stab 3</button>
                }
                {!props.improvedStab3 && !props.improvedStab2 &&
                    <button className="talent-jawn-inactive">Improved Stab 3</button>
                }
                {!props.improvedStab3 && props.improvedStab2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Stab 3", "Description", "available")}>Improved Stab 3</button>
                }


            </div>

            <div className="right-container-jawn">

                {props.improvedBackStab1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved BackStab 1", talentDescriptions("Improved BackStab 1"), "active")}>Improved BackStab 1</button>
                :
                <button className="talent-jawn-available"onClick={() => handleTalentClick("Improved BackStab 1", talentDescriptions("Improved BackStab 1"), "available")}>Improved BackStab 1</button>
                }

                {props.improvedBackStab2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved BackStab 2", "Description", "active")}>Improved BackStab 2</button>
                }
                {!props.improvedBackStab2 && !props.improvedBackStab1 &&
                    <button className="talent-jawn-inactive">Improved BackStab 2</button>
                }
                {!props.improvedBackStab2 && props.improvedBackStab1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved BackStab 2", "Description", "available")}>Improved BackStab 2</button>
                }

                {props.energized &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Energized", "Description", "active")}>Energized</button>
                }
                {!props.energized && !props.improvedBackStab2 &&
                    <button className="talent-jawn-inactive">Energized</button>
                }
                {!props.energized && props.improvedBackStab2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Energized", "Description", "available")}>Energized</button>
                }

            </div>
            </div>

            <div className="bottom-container-jawn">

            {props.organizedMess &&
                <button className="talent-jawn-active center-jawn" onClick={() => handleTalentClick("Organized Mess", "Description", "active")}>Organized Mess</button>
            }
            {!props.organizedMess && props.improvedStab2 && props.energized ||
            !props.organizedMess && props.improvedBackStab2 && props.improvedStab3 ?
                <button className="talent-jawn-available center-jawn" onClick={() => handleTalentClick("Organized Mess", "Description", "available")}>Organized Mess</button>
            :
                <button className="talent-jawn-inactive center-jawn">Organized Mess</button>
            }

            </div>
        </div>
    )
}
export default DexterityTree;