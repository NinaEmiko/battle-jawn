import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";

const ArcaneTree = ({props}:{props:any}) => {

    const handleTalentClick = (talent: string, description: string, type: string) => {
        props.setTalentPopUp(talent, description, type);
    }

    return (
        <div className="talent-group-jawn">

            <div className="left-and-right-container-jawn">

                <div className="left-container-jawn">

                    {props.talentTree.improvedFireBlast1 ?
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved FireBlast 1", talentDescriptions("Improved FireBlast 1"), "active")}>Improved FireBlast 1</button>
                    :
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved FireBlast 1", talentDescriptions("Improved FireBlast 1"), "available")}>Improved FireBlast 1</button>
                    }

                    {props.talentTree.improvedFireBlast2 &&
                        <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved FireBlast 2", talentDescriptions("Improved FireBlast 2"), "active")}>Improved FireBlast 2</button>
                    }
                    {!props.talentTree.improvedFireBlast2 && !props.talentTree.improvedFireBlast1 &&
                        <button className="talent-jawn-inactive">Improved FireBlast 2</button>
                    }
                    {!props.talentTree.improvedFireBlast2 && props.talentTree.improvedBlast1 &&
                        <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved FireBlast 2", talentDescriptions("Improved FireBlast 2"), "available")}>Improved FireBlast 2</button>
                    }

                    {props.talentTree.improvedIceBlast &&
                        <button className="talent-jawn-active" onClick={() => handleTalentClick("Improved IceBlast", talentDescriptions("Improved IceBlast"), "active")}>Improved IceBlast</button>
                    }
                    {!props.talentTree.improvedIceBlast && !props.talentTree.improvedFireBlast2 &&
                        <button className="talent-jawn-inactive">Improved IceBlast</button>
                    }
                    {!props.talentTree.improvedIceBlast && props.talentTree.improvedFireBlast2 &&
                        <button className="talent-jawn-available" onClick={() => handleTalentClick("Improved IceBlast", talentDescriptions("Improved IceBlast"), "available")}>Improved IceBlast</button>
                    }


                </div>

                <div className="right-container-jawn">

                    {props.talentTree.resourcefulness1 ?
                    <button className="talent-jawn-active" onClick={() => handleTalentClick("Resourcefulness 1", talentDescriptions("Resourcefulness 1"), "active")}>Resourcefulness 1</button>
                    :
                    <button className="talent-jawn-available" onClick={() => handleTalentClick("Resourcefulness 1", talentDescriptions("Resourcefulness 1"), "available")}>Resourcefulness 1</button>
                    }

                    {props.talentTree.resourcefulness2 &&
                        <button className="talent-jawn-active" onClick={() => handleTalentClick("Resourcefulness 2", talentDescriptions("Resourcefulness 2"), "active")}>Resourcefulness 2</button>
                    }
                    {!props.talentTree.resourcefulness2 && !props.talentTree.resourcefulness1 &&
                        <button className="talent-jawn-inactive">Resourcefulness 2</button>
                    }
                    {!props.talentTree.resourcefulness2 && props.talentTree.resourcefulness1 &&
                        <button className="talent-jawn-available" onClick={() => handleTalentClick("Resourcefulness 2", talentDescriptions("Resourcefulness 2"), "available")}>Resourcefulness 1</button>
                    }

                    {props.talentTree.frostBite &&
                        <button className="talent-jawn-active" onClick={() => handleTalentClick("FrostBite", talentDescriptions("FrostBite"), "active")}>FrostBite</button>
                    }
                    {!props.talentTree.frostBite && !props.talentTree.resourcefulness2 &&
                        <button className="talent-jawn-inactive">FrostBite</button>
                    }
                    {!props.talentTree.frostBite && props.talentTree.resourcefulness2 &&
                        <button className="talent-jawn-available" onClick={() => handleTalentClick("FrostBite", talentDescriptions("FrostBite"), "available")}>FrostBite</button>
                    }

                </div>
            </div>

            <div className="bottom-container-jawn">

                {props.talentTree.secondNature &&
                    <button className="talent-jawn-active center-jawn" onClick={() => handleTalentClick("Second Nature", talentDescriptions("Second Nature"), "active")}>Second Nature</button>
                }
                {!props.talentTree.secondNature && props.talentTree.improvedIceBlast && props.talentTree.resourcefulness2 ||
                !props.talentTree.secondNature && props.talentTree.improvedFireBlast2 && props.talentTree.frostBite ?
                    <button className="talent-jawn-available center-jawn"onClick={() => handleTalentClick("Second Nature", talentDescriptions("Second Nature"), "available")} >Second Nature</button>
                :
                    <button className="talent-jawn-inactive center-jawn">Second Nature</button>
                }

            </div>

        </div>
    )
}
export default ArcaneTree;