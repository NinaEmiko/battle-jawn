import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";

const SpiritualityTree = ({props}:{props:any}) => {

    const handleTalentClick = (talent: string, description: string, type: string) => {
        props.setTalentPopUp(talent, description, type);
    }

    return (
        <div className="talent-group-jawn">

            <div className="left-and-right-container-jawn">

            <div className="left-container-jawn">

                {props.improvedWand1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Wand 1", talentDescriptions("Improved Wand 1 - Spirituality"), "active")}>Improved Wand 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Wand 1", talentDescriptions("Improved Wand 1 - Spirituality"), "available")}>Improved Wand 1</button>
                }

                {props.improvedWand2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Wand 2", "Improved Wand 2 - Spirituality", "active")}>Improved Wand 2</button>
                }
                {!props.improvedWand2 && !props.improvedWand1 &&
                    <button className="talent-jawn-inactive">Improved Wand 2</button>
                }
                {!props.improvedWand2 && props.improvedWand1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Wand 2", "Improved Wand 2 - Spirituality", "available")}>Improved Wand 2</button>
                }

                {props.improvedWand3 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Wand 3", "Improved Wand 3 - Spirituality", "active")}>Improved Wand 3</button>
                }
                {!props.improvedWand3 && !props.improvedWand2 &&
                    <button className="talent-jawn-inactive">Improved Wand 3</button>
                }
                {!props.improvedWand3 && props.improvedWand2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Wand 3", "Improved Wand 3 - Spirituality", "available")}>Improved Wand 3</button>
                }


            </div>

            <div className="right-container-jawn">

                {props.holy1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Holy 1", talentDescriptions("Holy 1"), "active")}>Holy 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Holy 1", talentDescriptions("Holy 1"), "available")}>Holy 1</button>
                }

                {props.holy2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Holy 2", "Holy 2", "active")}>Holy 2</button>
                }
                {!props.holy2 && !props.holy1 &&
                    <button className="talent-jawn-inactive">Holy 2</button>
                }
                {!props.holy2 && props.holy1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Holy 2", "Holy 2", "available")}>Holy 2</button>
                }

                {props.holy3 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Holy 3", "Holy 3", "active")}>Holy 3</button>
                }
                {!props.holy3 && !props.holy2 &&
                    <button className="talent-jawn-inactive">Holy 3</button>
                }
                {!props.holy3 && props.holy2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Holy 3", "Holy 3", "available")}>Holy 3</button>
                }

            </div>
            </div>

            <div className="bottom-container-jawn">

            {props.spirituallyAttuned &&
                <button className="talent-jawn-active center-jawn" onClick={() => handleTalentClick("Spiritually Attuned", "Spiritually Attuned", "active")}>Spiritually Attuned</button>
            }
            {!props.spirituallyAttuned && props.improvedWand3 && props.improvedHoly2 ||
            !props.spirituallyAttuned && props.improvedWand2 && props.improvedHoly3 ?
                <button className="talent-jawn-available center-jawn" onClick={() => handleTalentClick("Spiritually Attuned", "Spiritually Attuned", "available")}>Spiritually Attuned</button>
            :
                <button className="talent-jawn-inactive center-jawn">Spiritually Attuned</button>
            }

            </div>
        </div>
    )
}
export default SpiritualityTree;