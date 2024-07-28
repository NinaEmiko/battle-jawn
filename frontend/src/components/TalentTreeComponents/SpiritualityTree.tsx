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

                {props.talentTree.improvedWand1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Wand 1", talentDescriptions("Improved Wand 1 - Spirituality"), "active")}>Improved Wand 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Wand 1", talentDescriptions("Improved Wand 1 - Spirituality"), "available")}>Improved Wand 1</button>
                }

                {props.talentTree.improvedWand2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Wand 2", "Improved Wand 2 - Spirituality", "active")}>Improved Wand 2</button>
                }
                {!props.talentTree.improvedWand2 && !props.talentTree.improvedWand1 &&
                    <button className="talent-jawn-inactive">Improved Wand 2</button>
                }
                {!props.talentTree.improvedWand2 && props.talentTree.improvedWand1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Wand 2", "Improved Wand 2 - Spirituality", "available")}>Improved Wand 2</button>
                }

                {props.talentTree.improvedWand3 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Wand 3", "Improved Wand 3 - Spirituality", "active")}>Improved Wand 3</button>
                }
                {!props.talentTree.improvedWand3 && !props.talentTree.improvedWand2 &&
                    <button className="talent-jawn-inactive">Improved Wand 3</button>
                }
                {!props.talentTree.improvedWand3 && props.talentTree.improvedWand2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Wand 3", "Improved Wand 3 - Spirituality", "available")}>Improved Wand 3</button>
                }


            </div>

            <div className="right-container-jawn">

                {props.talentTree.improvedHoly1 ?
                <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Holy 1", talentDescriptions("Improved Holy 1"), "active")}>Improved Holy 1</button>
                :
                <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Holy 1", talentDescriptions("Improved Holy 1"), "available")}>Improved Holy 1</button>
                }

                {props.talentTree.improvedHoly2 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Holy 2", "Improved Holy 2", "active")}>Improved Holy 2</button>
                }
                {!props.talentTree.improvedHoly2 && !props.talentTree.improvedHoly1 &&
                    <button className="talent-jawn-inactive">Improved Holy 2</button>
                }
                {!props.talentTree.improvedHoly2 && props.talentTree.improvedHoly1 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Holy 2", "Improved Holy 2", "available")}>Improved Holy 2</button>
                }

                {props.talentTree.improvedHoly3 &&
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved Holy 3", "Improved Holy 3", "active")}>Improved Holy 3</button>
                }
                {!props.talentTree.improvedHoly3 && !props.talentTree.improvedHoly2 &&
                    <button className="talent-jawn-inactive">Improved Holy 3</button>
                }
                {!props.talentTree.improvedHoly3 && props.talentTree.improvedHoly2 &&
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved Holy 3", "Improved Holy 3", "available")}>Improved Holy 3</button>
                }

            </div>
            </div>

            <div className="bottom-container-jawn">

            {props.talentTree.spirituallyAttuned &&
                <button className="talent-jawn-active center-jawn" onClick={() => handleTalentClick("Spiritually Attuned", "Spiritually Attuned", "active")}>Spiritually Attuned</button>
            }
            {!props.talentTree.spirituallyAttuned && props.talentTree.improvedWand3 && props.talentTree.improvedHoly2 ||
            !props.talentTree.spirituallyAttuned && props.talentTree.improvedWand2 && props.talentTree.improvedHoly3 ?
                <button className="talent-jawn-available center-jawn" onClick={() => handleTalentClick("Spiritually Attuned", "Spiritually Attuned", "available")}>Spiritually Attuned</button>
            :
                <button className="talent-jawn-inactive center-jawn">Spiritually Attuned</button>
            }

            </div>
        </div>
    )
}
export default SpiritualityTree;