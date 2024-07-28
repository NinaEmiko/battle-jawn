import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";

const StrengthTree = ({props}:{props:any}) => {

    const handleTalentClick = (talent: string, description: string, type: string) => {
        props.setTalentPopUp(talent, description, type);
    }

    return (
        <div className="talent-group-jawn">

            <div className="left-and-right-container-jawn">

            <div className="left-container-jawn">

                {props.talentTree.improvedStrike1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Strike 1", talentDescriptions("Improved Strike 1"), "active")}>Improved Strike 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Strike 1", talentDescriptions("Improved Strike 1"), "available")}>Improved Strike 1</button>
                }

                {props.talentTree.improvedStrike2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Strike 2", "Improved Strike 2", "active")}>Improved Strike 2</button>
                }
                {!props.talentTree.improvedStrike2 && !props.talentTree.improvedStrike1 &&
                    <button className="talent-jawn-inactive">Improved Strike 2</button>
                }
                {!props.talentTree.improvedStrike2 && props.talentTree.improvedStrike1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Strike 2", "Improved Strike 2", "available")}>Improved Strike 2</button>
                }

                {props.talentTree.improvedStrike3 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Strike 3", "Improved Strike 3", "active")}>Improved Strike 3</button>
                }
                {!props.talentTree.improvedStrike3 && !props.talentTree.improvedStrike2 &&
                    <button className="talent-jawn-inactive">Improved Strike 3</button>
                }
                {!props.talentTree.improvedStrike3 && props.talentTree.improvedStrike2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Strike 3", "Improved Strike 3", "available")}>Improved Strike 3</button>
                }


            </div>

            <div className="right-container-jawn">

                {props.talentTree.improvedImpale1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Impale 1", talentDescriptions("Improved Impale 1"), "active")}>Improved Impale 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Impale 1", talentDescriptions("Improved Impale 1"), "available")}>Improved Impale 1</button>
                }

                {props.talentTree.improvedImpale2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Impale 2", "Improved Impale 2", "active")}>Improved Impale 2</button>
                }
                {!props.talentTree.improvedImpale2 && !props.talentTree.improvedImpale1 &&
                    <button className="talent-jawn-inactive">Improved Impale 2</button>
                }
                {!props.talentTree.improvedImpale2 && props.talentTree.improvedImpale1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Impale 2", "Improved Impale 2", "available")}>Improved Impale 2</button>
                }

                {props.talentTree.improvedImpale3 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Impale 3", "Improved Impale 3", "active")}>Improved Impale 3</button>
                }
                {!props.talentTree.improvedImpale3 && !props.talentTree.improvedImpale2 &&
                    <button className="talent-jawn-inactive">Improved Impale 3</button>
                }
                {!props.talentTree.improvedImpale3 && props.talentTree.improvedImpale2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Impale 3", "Improved Impale 3", "available")}>Improved Impale 3</button>
                }

            </div>
            </div>

            <div className="bottom-container-jawn">

            {props.talentTree.titan &&
                <button className="talent-jawn-active center-jawn" onClick={() => handleTalentClick("Titan", "Titan", "active")}>Titan</button>
            }
            {!props.talentTree.titan && props.talentTree.improvedStrike3 && props.talentTree.improvedImpale2 ||
            !props.talentTree.titan && props.talentTree.improvedStrike2 && props.talentTree.improvedImpale3 ?
                <button className="talent-jawn-available center-jawn" onClick={() => handleTalentClick("Titan", "Titan", "available")}>Titan</button>
            :
                <button className="talent-jawn-inactive center-jawn">Titan</button>
            }

            </div>

        </div>

    )
}
export default StrengthTree;