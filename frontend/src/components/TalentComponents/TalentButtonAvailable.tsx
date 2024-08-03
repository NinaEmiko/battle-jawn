import "../../styling/TalentTree.css";
import { talentDescriptions } from "../../helpers/talent_tree_helper";

const TalentButtonAvailable = ({props}:{props:any}) => {


    return (
        <>
            <button
                className="talent-jawn-available"
                onClick={() =>
                    props.handleTalentClick(
                        props.text,
                        talentDescriptions(props.description),
                        "talent-jawn-available")}>
                            {props.text}
            </button>
        </>
    )
}
export default TalentButtonAvailable;