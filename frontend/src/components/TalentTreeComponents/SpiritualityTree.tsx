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

                {props.holy1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Holy 1", talentDescriptions("Holy 1"), "active")}>Holy 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Holy 1", talentDescriptions("Holy 1"), "available")}>Holy 1</button>
                }

                {props.holy2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Holy 2", "Description", "active")}>Holy 2</button>
                }
                {!props.holy2 && !props.holy1 &&
                    <button className="talent-jawn-inactive">Holy 2</button>
                }
                {!props.holy2 && props.holy1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Holy 2", "Description", "available")}>Holy 2</button>
                }

                {props.holy3 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Holy 3", "Description", "active")}>Holy 3</button>
                }
                {!props.holy3 && !props.holy2 &&
                    <button className="talent-jawn-inactive">Holy 3</button>
                }
                {!props.holy3 && props.holy2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Holy 3", "Description", "available")}>Holy 3</button>
                }

            </div>
            </div>

            <div className="bottom-container-jawn">

            {props.spirituallyAttuned &&
                <button className="talent-jawn-active center-jawn" onClick={() => handleTalentClick("Spiritually Attuned", "Description", "active")}>Spiritually Attuned</button>
            }
            {!props.spirituallyAttuned && props.improvedWand3 && props.improvedHoly2 ||
            !props.spirituallyAttuned && props.improvedWand2 && props.improvedHoly3 ?
                <button className="talent-jawn-available center-jawn" onClick={() => handleTalentClick("Spiritually Attuned", "Description", "available")}>Spiritually Attuned</button>
            :
                <button className="talent-jawn-inactive center-jawn">Spiritually Attuned</button>
            }

            </div>
        </div>
    )
}
export default SpiritualityTree;