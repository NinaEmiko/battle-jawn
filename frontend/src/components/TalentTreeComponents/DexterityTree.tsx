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

                {props.talentTree.improvedStab1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Stab 1", talentDescriptions("Improved Stab 1"), "active")}>Improved Stab 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Stab 1", talentDescriptions("Improved Stab 1"), "available")}>Improved Stab 1</button>
                }

                {props.talentTree.improvedStab2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Stab 2", "Improved Stab 2", "active")}>Improved Stab 2</button>
                }
                {!props.talentTree.improvedStab2 && !props.talentTree.improvedStab1 &&
                    <button className="talent-jawn-inactive">Improved Stab 2</button>
                }
                {!props.talentTree.improvedStab2 && props.talentTree.improvedStab1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Stab 2", "Improved Stab 2", "available")}>Improved Stab 2</button>
                }

                {props.talentTree.improvedStab3 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Stab 3", "Improved Stab 3", "active")}>Improved Stab 3</button>
                }
                {!props.talentTree.improvedStab3 && !props.talentTree.improvedStab2 &&
                    <button className="talent-jawn-inactive">Improved Stab 3</button>
                }
                {!props.talentTree.improvedStab3 && props.talentTree.improvedStab2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Stab 3", "Improved Stab 3", "available")}>Improved Stab 3</button>
                }


            </div>

            <div className="right-container-jawn">

                {props.talentTree.improvedBackStab1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved BackStab 1", talentDescriptions("Improved BackStab 1"), "active")}>Improved BackStab 1</button>
                :
                <button className="talent-jawn-available"onClick={() => handleTalentClick("Improved BackStab 1", talentDescriptions("Improved BackStab 1"), "available")}>Improved BackStab 1</button>
                }

                {props.talentTree.improvedBackStab2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved BackStab 2", "Improved BackStab 2", "active")}>Improved BackStab 2</button>
                }
                {!props.talentTree.improvedBackStab2 && !props.talentTree.improvedBackStab1 &&
                    <button className="talent-jawn-inactive">Improved BackStab 2</button>
                }
                {!props.talentTree.improvedBackStab2 && props.talentTree.improvedBackStab1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved BackStab 2", "Improved BackStab 2", "available")}>Improved BackStab 2</button>
                }

                {props.talentTree.energized &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Energized", "Energized", "active")}>Energized</button>
                }
                {!props.talentTree.energized && !props.talentTree.improvedBackStab2 &&
                    <button className="talent-jawn-inactive">Energized</button>
                }
                {!props.talentTree.energized && props.talentTree.improvedBackStab2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Energized", "Energized", "available")}>Energized</button>
                }

            </div>
            </div>

            <div className="bottom-container-jawn">

            {props.talentTree.organizedMess &&
                <button className="talent-jawn-active center-jawn" onClick={() => handleTalentClick("Organized Mess", "Organized Mess", "active")}>Organized Mess</button>
            }
            {!props.talentTree.organizedMess && props.talentTree.improvedStab2 && props.talentTree.energized ||
            !props.talentTree.organizedMess && props.talentTree.improvedBackStab2 && props.talentTree.improvedStab3 ?
                <button className="talent-jawn-available center-jawn" onClick={() => handleTalentClick("Organized Mess", "Organized Mess", "available")}>Organized Mess</button>
            :
                <button className="talent-jawn-inactive center-jawn">Organized Mess</button>
            }

            </div>
        </div>
    )
}
export default DexterityTree;