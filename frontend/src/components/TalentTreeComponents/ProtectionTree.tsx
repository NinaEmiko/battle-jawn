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

                {props.talentTree.improvedHeal1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Heal 1", talentDescriptions("Improved Heal 1"), "active")}>Improved Heal 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Heal 1", talentDescriptions("Improved Heal 1"), "available")}>Improved Heal 1</button>
                }

                {props.talentTree.improvedHeal2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Heal 2", "Improved Heal 2", "active")}>Improved Heal 2</button>
                }
                {!props.talentTree.improvedHeal2 && !props.talentTree.improvedHeal1 &&
                    <button className="talent-jawn-inactive">Improved Heal 2</button>
                }
                {!props.talentTree.improvedHeal2 && props.talentTree.improvedHeal1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Heal 2", "Improved Heal 2", "available")}>Improved Heal 2</button>
                }

                {props.talentTree.improvedHeal3 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Heal 3", "Improved Heal 3", "active")}>Improved Heal 3</button>
                }
                {!props.talentTree.improvedHeal3 && !props.talentTree.improvedHeal2 &&
                    <button className="talent-jawn-inactive">Improved Heal 3</button>
                }
                {!props.talentTree.improvedHeal3 && props.talentTree.improvedHeal2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Heal 3", "Improved Heal 3", "available")}>Improved Heal 3</button>
                }


            </div>

            <div className="right-container-jawn">

                {props.talentTree.botany1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Botany 1", talentDescriptions("Botany 1 - Protection"), "active")}>Botany 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Botany 1", talentDescriptions("Botany 1 - Protection"), "available")}>Botany 1</button>
                }

                {props.talentTree.botany2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Botany 2", "Botany 2 - Protection", "active")}>Botany 2</button>
                }
                {!props.talentTree.botany2 && !props.talentTree.botany1 &&
                    <button className="talent-jawn-inactive">Botany 2</button>
                }
                {!props.talentTree.botany2 && props.talentTree.botany1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Botany 2", "Botany 2 - Protection", "available")}>Botany 2</button>
                }

                {props.talentTree.bubble &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Bubble", "Bubble", "active")}>Bubble</button>
                }
                {!props.talentTree.bubble && !props.talentTree.botany2 &&
                    <button className="talent-jawn-inactive">Bubble</button>
                }
                {!props.talentTree.bubble && props.talentTree.botany2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Bubble", "Bubble", "available")}>Bubble</button>
                }

            </div>
            </div>

            <div className="bottom-container-jawn">

            {props.survivalInstincts &&
                <button className="talent-jawn-active center-jawn" onClick={() => handleTalentClick("Survival Instincts", "Survival Instincts", "active")}>Survival Instincts</button>
            }
            {!props.survivalInstincts && props.improvedHeal3 && props.botany2 ||
            !props.survivalInstincts && props.improvedHeal2 && props.bubble ?
                <button className="talent-jawn-available center-jawn" onClick={() => handleTalentClick("Survival Instincts", "Survival Instincts", "available")}>Survival Instincts</button>
            :
                <button className="talent-jawn-inactive center-jawn">Survival Instincts</button>
            }

            </div>

        </div>
    )
}
export default ProtectionTree;