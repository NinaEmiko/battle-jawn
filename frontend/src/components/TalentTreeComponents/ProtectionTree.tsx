import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";

const ProtectionTree = ({props}:{props:any}) => {

    const handleTalentClick = (talent: string, description: string, type: string) => {
        props.setTalentPopUp(talent, description, type);
    }

    return (
        <div className="talent-group-jawn">

            <div className="left-and-right-container-jawn">

            <div className="left-container-jawn">

                {props.improvedHeal1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Heal 1", talentDescriptions("Improved Heal 1"), "active")}>Improved Heal 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Heal 1", talentDescriptions("Improved Heal 1"), "available")}>Improved Heal 1</button>
                }

                {props.improvedHeal2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Heal 2", "Description", "active")}>Improved Heal 2</button>
                }
                {!props.improvedHeal2 && !props.improvedHeal1 &&
                    <button className="talent-jawn-inactive">Improved Heal 2</button>
                }
                {!props.improvedHeal2 && props.improvedHeal1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Heal 2", "Description", "available")}>Improved Heal 2</button>
                }

                {props.improvedHeal3 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Heal 3", "Description", "active")}>Improved Heal 3</button>
                }
                {!props.improvedHeal3 && !props.improvedHeal2 &&
                    <button className="talent-jawn-inactive">Improved Heal 3</button>
                }
                {!props.improvedHeal3 && props.improvedHeal2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Heal 3", "Description", "available")}>Improved Heal 3</button>
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

                {props.bubble &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Bubble", "Description", "active")}>Bubble</button>
                }
                {!props.bubble && !props.botany2 &&
                    <button className="talent-jawn-inactive">Bubble</button>
                }
                {!props.bubble && props.botany2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Bubble", "Description", "available")}>Bubble</button>
                }

            </div>
            </div>

            <div className="bottom-container-jawn">

            {props.survivalInstincts &&
                <button className="talent-jawn-active center-jawn" onClick={() => handleTalentClick("Survival Instincts", "Description", "active")}>Survival Instincts</button>
            }
            {!props.survivalInstincts && props.improvedHeal3 && props.botany2 ||
            !props.survivalInstincts && props.improvedHeal2 && props.bubble ?
                <button className="talent-jawn-available center-jawn" onClick={() => handleTalentClick("Survival Instincts", "Description", "available")}>Survival Instincts</button>
            :
                <button className="talent-jawn-inactive center-jawn">Survival Instincts</button>
            }

            </div>

        </div>
    )
}
export default ProtectionTree;