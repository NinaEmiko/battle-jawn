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

                {props.improvedStrike1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Strike 1", talentDescriptions("Improved Strike 1"), "active")}>Improved Strike 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Strike 1", talentDescriptions("Improved Strike 1"), "available")}>Improved Strike 1</button>
                }

                {props.improvedStrike2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Strike 2", "Description", "active")}>Improved Strike 2</button>
                }
                {!props.improvedStrike2 && !props.improvedStrike1 &&
                    <button className="talent-jawn-inactive">Improved Strike 2</button>
                }
                {!props.improvedStrike2 && props.improvedStrike1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Strike 2", "Description", "available")}>Improved Strike 2</button>
                }

                {props.improvedStrike3 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Strike 3", "Description", "active")}>Improved Strike 3</button>
                }
                {!props.improvedStrike3 && !props.improvedStrike2 &&
                    <button className="talent-jawn-inactive">Improved Strike 3</button>
                }
                {!props.improvedStrike3 && props.improvedStrike2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Strike 3", "Description", "available")}>Improved Strike 3</button>
                }


            </div>

            <div className="right-container-jawn">

                {props.improvedImpale1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Impale 1", talentDescriptions("Improved Impale 1"), "active")}>Improved Impale 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Impale 1", talentDescriptions("Improved Impale 1"), "available")}>Improved Impale 1</button>
                }

                {props.improvedImpale2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Impale 2", "Description", "active")}>Improved Impale 2</button>
                }
                {!props.improvedImpale2 && !props.improvedImpale1 &&
                    <button className="talent-jawn-inactive">Improved Impale 2</button>
                }
                {!props.improvedImpale2 && props.improvedImpale1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Impale 2", "Description", "available")}>Improved Impale 2</button>
                }

                {props.improvedImpale3 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Impale 3", "Description", "active")}>Improved Impale 3</button>
                }
                {!props.improvedImpale3 && !props.improvedImpale2 &&
                    <button className="talent-jawn-inactive">Improved Impale 3</button>
                }
                {!props.improvedImpale3 && props.improvedImpale2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Impale 3", "Description", "available")}>Improved Impale 3</button>
                }

            </div>
            </div>

            <div className="bottom-container-jawn">

            {props.titan &&
                <button className="talent-jawn-active center-jawn" onClick={() => handleTalentClick("Titan", "Description", "active")}>Titan</button>
            }
            {!props.titan && props.improvedStrike3 && props.improvedImpale2 ||
            !props.titan && props.improvedStrike2 && props.improvedImpale3 ?
                <button className="talent-jawn-available center-jawn" onClick={() => handleTalentClick("Titan", "Description", "available")}>Titan</button>
            :
                <button className="talent-jawn-inactive center-jawn">Titan</button>
            }

            </div>

        </div>

    )
}
export default StrengthTree;