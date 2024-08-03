import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";

const TalentButtonActive = ({props}:{props:any}) => {


    return (
        <>
            <button
                className="talent-jawn-active"
                onClick={() =>
                    props.handleTalentClick(
                        props.text,
                        talentDescriptions(props.description),
                        "talent-jawn-active")}>
                            {props.text}
            </button>
        </>
    )
}
export default TalentButtonActive;